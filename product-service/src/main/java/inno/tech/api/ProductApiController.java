package inno.tech.api;

import com.fasterxml.jackson.databind.JsonNode;
import inno.tech.exception.ProductNotFoundException;
import inno.tech.model.ProductInfo;
import inno.tech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("/product/{product_id}")
    public Mono<ProductInfo> getProductInfo(@PathVariable("product_id") String productId) {
        Mono<JsonNode> liveApiData = productService.findLiveApiData(productId);
        Mono<JsonNode> rateReport = productService.findRateReport(productId);
        return Mono.zip(liveApiData, rateReport, ProductInfo::new);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal arguments")
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgumentHandler() {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
    @ExceptionHandler(ProductNotFoundException.class)
    public void productNotFound() {
    }
}
