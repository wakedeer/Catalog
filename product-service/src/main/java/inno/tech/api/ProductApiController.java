package inno.tech.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final OAuth2RestTemplate oAuth2RestTemplate;

    @GetMapping("/product/{product_id}")
    public ResponseEntity<String> getProductInfo(@PathVariable("product_id") String productId) {
        var review = oAuth2RestTemplate.getForObject("http://localhost:8083/review/1", String.class);

        //TODO
        return ResponseEntity.ok("OK with " + productId + " " + review);
    }
}
