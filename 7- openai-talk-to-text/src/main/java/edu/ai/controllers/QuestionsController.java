package edu.ai.controllers;

import edu.ai.models.Question;
import edu.ai.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionsController {

    private final OpenAIService openAIService;

    @PostMapping(value = "/talk", produces = "audio/mpeg")
    public byte[] talkTalk(@RequestBody Question question) {
        return openAIService.getSpeech(question);
    }
}