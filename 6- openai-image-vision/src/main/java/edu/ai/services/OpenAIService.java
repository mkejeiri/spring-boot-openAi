package edu.ai.services;

import edu.ai.models.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAIService {
    byte[] getImageGeneric(Question question);

    byte[] getImageOpenAiSpecific(Question question);

    String getDescription(MultipartFile file);


}
