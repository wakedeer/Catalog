package inno.tech.api;

import inno.tech.model.Review;
import inno.tech.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewApiController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public ResponseEntity<Iterable<Review>> getAll() {
        return ResponseEntity.ok(reviewRepository.findAll());
    }

    @GetMapping("/reviews/{review_id}")
    public ResponseEntity<Review> get(@PathVariable("review_id") Long reviewId) {
        return reviewRepository.findById(reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> add(@RequestBody Review review) {
        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @PutMapping("/reviews/{review_id}")
    public ResponseEntity<Review> edit(@RequestBody Review newReview, @PathVariable("review_id") Long reviewId) {
        var savedReview = reviewRepository.findById(reviewId)
                .map(review -> {
                    review.setScore(newReview.getScore());
                    review.setDetails(newReview.getDetails());
                    return reviewRepository.save(review);
                }).orElseGet(() -> {
                    newReview.setId(reviewId);
                    return reviewRepository.save(newReview);
                });
        return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/reviews/{review_id}")
    public ResponseEntity<Void> delete(@PathVariable("review_id") Long reviewId) {
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.noContent().build();
    }
}
