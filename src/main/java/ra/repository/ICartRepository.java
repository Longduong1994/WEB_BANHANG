package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Cart;
@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
}
