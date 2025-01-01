package edu.ai.rag.expert.services;

import edu.ai.rag.expert.models.Answer;
import edu.ai.rag.expert.models.Question;

public interface OpenAIService {
    Answer getAnswer(Question question);

}
