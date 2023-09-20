package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.model.entity.Size;
@Repository
public interface ISizeRepository extends JpaRepository<Size,Long> {
    boolean existsByName(String name);

}
