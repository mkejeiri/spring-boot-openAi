package edu.ai.functions;

import edu.ai.models.StockPriceRequest;
import edu.ai.models.StockPriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Configuration
public class StockPriceServiceFunction implements Function<StockPriceRequest, StockPriceResponse> {

    public static final String STOCK_PRICE_URL = "https://api.api-ninjas.com/v1/stockprice";

    @Value("${spring.application.ninja.api-key}")
    private String apiNinjasKey;

    @Override
    public StockPriceResponse apply(StockPriceRequest stockPriceRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(STOCK_PRICE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for weather request: " + stockPriceRequest);

            uriBuilder.queryParam("ticker", stockPriceRequest.ticker());

//            if (stockPriceRequest.currency() != null && !stockPriceRequest.currency().isBlank()) {
//                uriBuilder.queryParam("currency", stockPriceRequest.currency());
//            }
//            if (stockPriceRequest.name() != null && !stockPriceRequest.name().isBlank()) {
//                uriBuilder.queryParam("country", stockPriceRequest.name());
//            }
            return uriBuilder.build();
        }).retrieve().body(StockPriceResponse.class);
    }
}