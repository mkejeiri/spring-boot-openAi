package edu.ai.openaipromptengineering.models;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(
        @JsonPropertyDescription("This is the city name") //adding a property description into city name
        String answer) {
}