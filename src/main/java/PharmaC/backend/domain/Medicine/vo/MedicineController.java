package PharmaC.backend.domain.Medicine.vo;

import PharmaC.backend.domain.Medicine.domain.Medicine;
import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import PharmaC.backend.domain.Medicine.dto.MedicineDataDTO;
import PharmaC.backend.domain.Medicine.dto.PageInfoDTO;
import PharmaC.backend.domain.Medicine.dto.response.ResponseApiDTO;
import PharmaC.backend.domain.Medicine.dto.response.ResponseOneApiDTO;
import PharmaC.backend.domain.Medicine.exception.MedicineErrorCode;
import PharmaC.backend.domain.Medicine.exception.MedicineNotFound;
import PharmaC.backend.domain.Medicine.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Operation(summary = "약품 db로 가져오기")
    @GetMapping("/get-all") // pharma-c/medicine/get-all
    public void saveAllMedicines() throws IOException {
       medicineService.saveAllMedicine();
    }

    @Operation(summary = "의약품 전체조회")
    @GetMapping()
    public ResponseEntity<ResponseApiDTO> getAllMedicines(Pageable pageable) {
        try {
            Page<Medicine> medicines = medicineService.getAllMedicines(pageable);

            List<Medicine> content = medicines.getContent();
            PageInfoDTO pageInfo = new PageInfoDTO(
                    medicines.getNumber() + 1,
                    medicines.getSize(),
                    medicines.getTotalPages(),
                    (int) medicines.getTotalElements()
            );

            ResponseApiDTO response = new ResponseApiDTO(200, true, "의약품 불러오기에 성공하였습니다.", new MedicineDataDTO(content, pageInfo));

            return ResponseEntity.ok(response);
        } catch (MedicineNotFound e) {
            MedicineErrorCode errorCode = (MedicineErrorCode) e.getErrorCode();
            ResponseApiDTO response = new ResponseApiDTO(
                    errorCode.getStatus().value(),
                    false,
                    errorCode.getReason(),
                    null
            );

            return ResponseEntity.status(errorCode.getStatus()).body(response);
        }
    }

    @Operation(summary = "의약품 코드로 조회(1개)")
    @GetMapping("items/{code}")
    public ResponseEntity<ResponseOneApiDTO> getMedicineByCode(@PathVariable String code) {

        try {
            Medicine medicine = medicineService.findByCode(code);

            ResponseOneApiDTO response = new ResponseOneApiDTO(200, true, "의약품 코드로 1개 조회 성공", medicine);

            return ResponseEntity.ok(response);
        } catch(MedicineNotFound e) {
            MedicineErrorCode errorCode = (MedicineErrorCode) e.getErrorCode();
            ResponseOneApiDTO response = new ResponseOneApiDTO(
                    errorCode.getStatus().value(),
                    false,
                    errorCode.getReason(),
                    null
            );

            return ResponseEntity.status(errorCode.getStatus()).body(response);
        }
    }

    @Operation(summary = "의약품 검색")
    @GetMapping("/{search}")
    public ResponseEntity<ResponseApiDTO> getSearchedMedicines(
            @PathVariable String search,
            Pageable pageable) {
        try {
            Page<Medicine> medicines = medicineService.getSearchedMedicines(search, pageable);

            PageInfoDTO pageInfo = new PageInfoDTO(
                    medicines.getNumber() + 1,
                    medicines.getSize(),
                    medicines.getTotalPages(),
                    (int) medicines.getTotalElements()
            );

            MedicineDataDTO data = new MedicineDataDTO(medicines.getContent(), pageInfo);

            ResponseApiDTO response = new ResponseApiDTO(200, true, "의약품 불러오기에 성공하였습니다.", data);

            return ResponseEntity.ok(response);
        } catch (MedicineNotFound e) {
            MedicineErrorCode errorCode = (MedicineErrorCode) e.getErrorCode();
            ResponseApiDTO response = new ResponseApiDTO(
                    errorCode.getStatus().value(),
                    false,
                    errorCode.getReason(),
                    null
            );

            return ResponseEntity.status(errorCode.getStatus()).body(response);
        }
    }

}
