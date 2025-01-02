package edu.ai.services;

import edu.ai.models.Question;

public interface OpenAIService {
    byte[] getSpeech(Question question);
}
