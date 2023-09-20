package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Payment;
@Repository
public interface IPaymentRepository extends JpaRepository<Payment,Long> {
    boolean existsByName(String name);
}
