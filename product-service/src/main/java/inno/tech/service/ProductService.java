package inno.tech.service;

import lombok.NonNull;
import org.codehaus.jackson.JsonNode;

import java.util.Optional;

public interface ProductService {
    Optional<JsonNode> findProductData(@NonNull String productId);
}
