package inno.tech.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NonNull;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<JsonNode> findLiveApiData(@NonNull String productId);

    Mono<JsonNode> findRateReport(@NonNull String productId);
}
