package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Brand;
@Repository
public interface IBrandRepository extends JpaRepository<Brand,Long> {
    boolean existsByName(String name);
}
