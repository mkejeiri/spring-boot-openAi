package edu.ai.rag.services;

import edu.ai.rag.models.Answer;
import edu.ai.rag.models.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OpenAIServiceRagImpl implements OpenAIRagService {

    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Value("classpath:templates/rag-prompt-template-meta.st")
    private Resource ragPromptTemplateMeta;

    @Override
    public Answer getAnswer(Question question) {
        return getAnswerInternal(question, ragPromptTemplate);
    }

    @Override
    public Answer getAnswerWithMetaData(Question question) {
        return getAnswerInternal(question, ragPromptTemplateMeta);
    }

    private Answer getAnswerInternal(Question question, Resource promptFile) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question()).topK(5).build());
        List<String> contentList = documents.stream().map(Document::getContent).toList();

        PromptTemplate promptTemplate = new PromptTemplate(promptFile);
        Prompt prompt = promptTemplate.create(Map.of("input", question.question(), "documents",
                String.join("\n", contentList)));

        contentList.forEach(System.out::println);

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }
}
