package edu.ai.openaipromptengineering.services;

import edu.ai.openaipromptengineering.models.Answer;
import edu.ai.openaipromptengineering.models.GetCapitalRequest;
import edu.ai.openaipromptengineering.models.GetCapitalResponse;
import edu.ai.openaipromptengineering.models.Question;

public interface OpenAIService {
    String getAnswer(String questions);
    Answer getAnswer(Question question);
    Answer getCapital(GetCapitalRequest getCapitalRequest);
    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

    Answer getAnswerJson(GetCapitalRequest getCapitalRequest);
    GetCapitalResponse getCapitalFormatted(GetCapitalRequest getCapitalRequest);
}
