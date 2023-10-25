package PharmaC.backend.domain.Medicine.mapper;

import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import PharmaC.backend.domain.Medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping("/search")
    public List<MedicineDTO> getAllMedicines() throws IOException {
       return medicineService.getAllMedicine();
    }
}
