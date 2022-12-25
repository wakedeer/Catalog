package inno.tech.repository;

import inno.tech.model.Review;
import inno.tech.model.ReviewReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Query("select new inno.tech.model.ReviewReport(count(r), avg(r.score)) FROM Review r where r.productId = :id group by r.productId")
    ReviewReport generateStat(@Param("id") String productId);

    List<Review> findAllByProductId(String productId);
}
