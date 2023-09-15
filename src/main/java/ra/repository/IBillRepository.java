package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Bill;
@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {
}
