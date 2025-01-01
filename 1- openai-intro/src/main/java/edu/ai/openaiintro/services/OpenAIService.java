package edu.ai.openaiintro.services;

import edu.ai.openaiintro.models.Answer;
import edu.ai.openaiintro.models.GetCapitalRequest;
import edu.ai.openaiintro.models.GetCapitalResponse;
import edu.ai.openaiintro.models.Question;

public interface OpenAIService {
    String getAnswer(String questions);
    Answer getAnswer(Question question);
    Answer getCapital(GetCapitalRequest getCapitalRequest);
    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

    Answer getAnswerJson(GetCapitalRequest getCapitalRequest);
    GetCapitalResponse getCapitalFormatted(GetCapitalRequest getCapitalRequest);
}
