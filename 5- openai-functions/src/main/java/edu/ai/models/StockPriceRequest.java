package edu.ai.models;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API request")
public record StockPriceRequest(@JsonProperty(required = true,
        value = "ticker") @JsonPropertyDescription("The company ticker e.g AAPL for Apple") String ticker,
                                @JsonProperty(required = false) @JsonPropertyDescription("Optional Company name") String name,
                                @JsonProperty(required = false) @JsonPropertyDescription("Optional currency") String currency) {
}