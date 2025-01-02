package edu.ai.services;

import edu.ai.models.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    private final ImageModel imageModel;
    private final OpenAiImageModel openAiImageModel;
    private final ChatModel chatModel;

    @Override
    public byte[] getImageGeneric(Question question) {

        //change options builder
        var options = optionsBuilderGeneric();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }

    @Override
    public byte[] getImageOpenAiSpecific(Question question) {

        //change options builder
        var options = openAiImageOptionsBuilder();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = openAiImageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }

    @Override
    public String getDescription(MultipartFile file) {

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_TURBO_2024_04_09.getValue())
                .build();

        var userMessage = new UserMessage("Explain what do you see in this picture?",
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG, file.getResource())));

        ChatResponse response = chatModel.call(new Prompt(List.of(userMessage), options));

        return response.getResult().getOutput().toString();
    }

    private OpenAiImageOptions openAiImageOptionsBuilder() {
        return OpenAiImageOptions.builder()
                .withHeight(1024).withWidth(1792)
                .withResponseFormat("b64_json")
                .withModel(OpenAiImageApi.ImageModel.DALL_E_3.getValue())
                .withQuality("hd") //default standard
                //.withStyle("natural") //default vivid
                .build();
    }

    private ImageOptions optionsBuilderGeneric() {
        return ImageOptionsBuilder.builder()
                .height(1024)
                .width(1024)
                .responseFormat("b64_json")
                .model(OpenAiImageApi.ImageModel.DALL_E_3.getValue())
                .style("natural") //default vivid
                .build();
        //
    }
}