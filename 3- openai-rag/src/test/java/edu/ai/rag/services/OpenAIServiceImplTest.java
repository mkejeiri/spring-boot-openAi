package edu.ai.rag.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAIServiceImplTest {
    @Autowired
    OpenAIService openAIService;

    @Test
    void getAnswer() {
//        String answer = openAIService.getAnswer("tell me a dad joke");
//        String answer = openAIService.getAnswer("Write a python script to output numbers from 1 to 100.");
//        String answer = openAIService.getAnswer("Write the game snake in python.");
        String answer = openAIService.getAnswer("Create JSON for the following: There are 3 people, two males. One is named Mark. Another is named Joe. And a third person is a woman named Sam. The woman is age 20 and the two men are both 19.");
        System.out.println("Answered");
        System.out.println(answer);
    }
}