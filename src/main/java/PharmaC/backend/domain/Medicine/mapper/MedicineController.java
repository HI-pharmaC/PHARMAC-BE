package PharmaC.backend.domain.Medicine.mapper;

import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import PharmaC.backend.domain.Medicine.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    // 약품 전체 조회
    @GetMapping() // pharma-c/medicine
    public List<MedicineDTO> getAllMedicines() throws IOException {
       return medicineService.getAllMedicine();
    }

    @GetMapping("/item/{code}")
    public List<MedicineDTO> getSelectedMedicine(@PathVariable Long code) throws IOException {
        return medicineService.getSelectedMedicine(code);
    }

//    @GetMapping("/item/{name}")
//    public List<MedicineDTO> getSearchedMedicineByName(@PathVariable String name) throws IOException {
//        return medicineService.getSearchedMedicineByName(name);
//    }

//    @GetMapping("/{code}") // pharma-c/medicine/{code}
//    public List<MedicineDTO> getSelectedMedicines(@PathVariable int code) throws IOException {
//        return medicineService.getSelectedMedicines(code);
//    }
}
