package edu.ai.services;

import edu.ai.models.Answer;
import edu.ai.models.Question;

public interface OpenAIService {
    Answer getAnswer(Question question);
    Answer getStockPrice(Question question);

}
