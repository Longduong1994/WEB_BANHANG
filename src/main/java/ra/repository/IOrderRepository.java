package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Order;
@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
}
