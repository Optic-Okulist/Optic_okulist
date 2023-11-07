package spring.boot.optic.okulist.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.boot.optic.okulist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u LEFT JOIN FETCH u.roles r "
            + "WHERE u.email = :email "
            + "AND u.isDeleted = FALSE "
            + "AND r.isDeleted = FALSE")
    Optional<User> findByEmail(String email);
}
