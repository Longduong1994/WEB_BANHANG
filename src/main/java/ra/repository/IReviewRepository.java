package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Review;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByWriter(String writer);
}
