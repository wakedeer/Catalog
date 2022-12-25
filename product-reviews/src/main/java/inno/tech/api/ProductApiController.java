package inno.tech.api;

import inno.tech.model.Review;
import inno.tech.model.ReviewReport;
import inno.tech.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/products/{product_id}/reviews")
    public ResponseEntity<Iterable<Review>> getAllByProductId(@PathVariable("product_id") String productId) {
        return ResponseEntity.ok(reviewRepository.findAllByProductId(productId));
    }

    @GetMapping("/products/{product_id}/reviews/report")
    public ResponseEntity<ReviewReport> createReport(@PathVariable("product_id") String productId) {
        var stat = reviewRepository.generateStat(productId);
        return ResponseEntity.ok(stat);
    }
}
