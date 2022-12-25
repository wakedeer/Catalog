package inno.tech.service;

import com.fasterxml.jackson.databind.JsonNode;
import inno.tech.config.ExternalApiProperties;
import inno.tech.exception.ProductNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final OAuth2RestTemplate oAuth2RestTemplate;

    private final Scheduler scheduler;

    private final WebClient adidasWebClient;

    private final ExternalApiProperties externalApiProperties;

    @Override
    public Mono<JsonNode> findLiveApiData(@NonNull String productId) {
        return adidasWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(externalApiProperties.getAdidas().getBasePath()).pathSegment(productId).build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .subscribeOn(scheduler);
    }

    @Override
    public Mono<JsonNode> findRateReport(@NonNull String productId) {
        return Mono.fromCallable(() -> retrieveReviewReport(productId))
                .subscribeOn(scheduler);
    }

    private JsonNode retrieveReviewReport(String productId) {
        var host = externalApiProperties.getReview().getHost();
        var path = String.format(externalApiProperties.getReview().getReportPath(), productId);
        return Optional.ofNullable(oAuth2RestTemplate.getForObject(host + path, JsonNode.class))
                .orElseThrow(() -> new ProductNotFoundException("product with id " + productId + " hasn't found"));
    }
}
