package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.OrderStatus;

public interface IOrderStatusRepository extends JpaRepository<OrderStatus,Integer> {
}
