package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Cart;
import ra.model.entity.Order;
import ra.model.entity.ProductSamples;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByOrder(Order order);

    List<Cart> findAllByOrder(Order order);

   List<Cart> findByProductSamples(ProductSamples productSamples);
}
