package PharmaC.backend.domain.User.repository;

import PharmaC.backend.domain.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findBySiteId(String id);
    //Optional<User> findById(String id);
    Boolean existsBySiteId(String id);


}
