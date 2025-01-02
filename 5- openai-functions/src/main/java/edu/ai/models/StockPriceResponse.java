package edu.ai.models;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record StockPriceResponse(@JsonPropertyDescription("Stock ticker") String ticker,
                                 @JsonPropertyDescription("name of the company") String name,
                                 @JsonPropertyDescription("Stock price") BigDecimal price,
                                 @JsonPropertyDescription("Updated at") Integer updated,
                                 @JsonPropertyDescription("currency listed in") String currency) {
}
