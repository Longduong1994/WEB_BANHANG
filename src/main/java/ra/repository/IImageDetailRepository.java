package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.dto.response.ImageProductResponseDto;
import ra.model.entity.ImageDetails;
import ra.model.entity.Product;

import java.util.List;

public interface IImageDetailRepository extends JpaRepository<ImageDetails,Long> {
    List<ImageDetails> findAllByProduct(Product product);

}
