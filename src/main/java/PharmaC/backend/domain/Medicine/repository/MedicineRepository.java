package PharmaC.backend.domain.Medicine.repository;

import PharmaC.backend.domain.Medicine.domain.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Page<Medicine> findByItemCode(String code, Pageable pageable);

    @Query("SELECT m FROM Medicine m WHERE m.name LIKE %:keyword% OR m.effect LIKE %:keyword%")
    Page<Medicine> findBySearching(@Param("keyword") String keyword, Pageable pageable);
}
