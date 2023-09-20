package ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.model.entity.RoleName;
import ra.model.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Users> findByUsername(String username);
    Users findByEmail(String email);
    @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.roleName = :roleName")
    List<Users> findAllUsersWithUserRole(RoleName roleName);
}
