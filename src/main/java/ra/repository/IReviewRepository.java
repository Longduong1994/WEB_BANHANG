package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Review;
@Repository
public interface IReviewRepository extends JpaRepository<Review,Long> {
}
