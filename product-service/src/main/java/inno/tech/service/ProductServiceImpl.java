package inno.tech.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Override
    public Optional<JsonNode> findProductData(@NonNull String productId) {

        return Optional.empty();
    }
}
