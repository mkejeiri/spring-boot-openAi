package edu.ai.rag.controllers;


import edu.ai.rag.models.Answer;
import edu.ai.rag.models.Question;
import edu.ai.rag.services.OpenAIRagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("openairag")
@RequiredArgsConstructor
public class RagQuestionsController {
    private final OpenAIRagService openAIRagService;

    @PostMapping("rag/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIRagService.getAnswer(question);
    }

    @PostMapping("rag-meta/ask")
    public Answer askQuestionWithMeta(@RequestBody Question question) {
        return openAIRagService.getAnswerWithMetaData(question);
    }
}
