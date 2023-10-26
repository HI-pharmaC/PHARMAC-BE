package PharmaC.backend.domain.History.repository;

import PharmaC.backend.domain.History.domain.History;
import PharmaC.backend.domain.User.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoryRepository extends JpaRepository<History, Long>, JpaSpecificationExecutor {
    List<History> findAllByUser(User user);
}
