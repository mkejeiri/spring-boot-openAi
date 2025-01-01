package edu.ai.rag.services;

import edu.ai.rag.models.Answer;
import edu.ai.rag.models.GetCapitalRequest;
import edu.ai.rag.models.GetCapitalResponse;
import edu.ai.rag.models.Question;

public interface OpenAIService {
    String getAnswer(String questions);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest);

    Answer getAnswerJson(GetCapitalRequest getCapitalRequest);

    GetCapitalResponse getCapitalFormatted(GetCapitalRequest getCapitalRequest);
}
