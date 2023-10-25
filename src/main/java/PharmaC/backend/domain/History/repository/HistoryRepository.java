package PharmaC.backend.domain.History.repository;

import PharmaC.backend.domain.History.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoryRepository extends JpaRepository<History, Long>, JpaSpecificationExecutor {
    
}
