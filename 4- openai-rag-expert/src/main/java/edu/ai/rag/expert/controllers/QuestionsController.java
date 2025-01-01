package edu.ai.rag.expert.controllers;

import edu.ai.rag.expert.models.Answer;
import edu.ai.rag.expert.models.Question;
import edu.ai.rag.expert.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("openai")
@RequiredArgsConstructor
public class QuestionsController {


    private final OpenAIService openAIService;

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

}
