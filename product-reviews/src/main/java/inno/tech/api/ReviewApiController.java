package inno.tech.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewApiController {

    @GetMapping("/review/{product_id}")
    public ResponseEntity<String> getReview(@PathVariable("product_id") String productId) {
        //TODO
        return ResponseEntity.ok("Review info" + productId);
    }
}
