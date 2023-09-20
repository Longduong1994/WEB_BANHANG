package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Order;
import ra.model.entity.Users;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    boolean existsByUsers( Users user);
    List<Order> findAllByUsers(Users user);
}
