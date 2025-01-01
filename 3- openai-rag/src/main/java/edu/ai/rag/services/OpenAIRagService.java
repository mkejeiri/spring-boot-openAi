package edu.ai.rag.services;

import edu.ai.rag.models.Answer;
import edu.ai.rag.models.Question;

public interface OpenAIRagService {
    Answer getAnswer(Question questions);

    Answer getAnswerWithMetaData(Question question);
}
