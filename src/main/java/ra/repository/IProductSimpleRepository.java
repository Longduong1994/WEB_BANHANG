package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.dto.response.ProductSimpleResponseDto;
import ra.model.entity.ProductSamples;
import ra.model.entity.Product;

import java.util.List;

@Repository
public interface IProductSimpleRepository extends JpaRepository<ProductSamples,Long> {
    List<ProductSamples> findAllByProduct(Product product);
    @Query("SELECT p FROM ProductSamples p JOIN Cart c ON p.id = c.productSamples.id GROUP BY p.id ORDER BY SUM(c.quantity) DESC")
    ProductSamples findProductWithMostSales();
}
