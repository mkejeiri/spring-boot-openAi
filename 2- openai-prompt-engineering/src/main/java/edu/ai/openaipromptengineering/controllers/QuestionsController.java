package edu.ai.openaipromptengineering.controllers;


import edu.ai.openaipromptengineering.models.Answer;
import edu.ai.openaipromptengineering.models.GetCapitalRequest;
import edu.ai.openaipromptengineering.models.GetCapitalResponse;
import edu.ai.openaipromptengineering.models.Question;
import edu.ai.openaipromptengineering.services.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;


@RestController("openai")
public class QuestionsController {

    @Autowired
    private final OpenAIService openAIService;

    public QuestionsController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return this.openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return this.openAIService.getCapitalWithInfo(getCapitalRequest);
    }

    @PostMapping("/askJson")
    public Answer askQuestionJson(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getAnswerJson(getCapitalRequest);
    }

    @PostMapping("/askFormatted")
    public GetCapitalResponse askQuestionFormatted(@RequestBody GetCapitalRequest getCapitalRequest) {
        return openAIService.getCapitalFormatted(getCapitalRequest);
    }

}
