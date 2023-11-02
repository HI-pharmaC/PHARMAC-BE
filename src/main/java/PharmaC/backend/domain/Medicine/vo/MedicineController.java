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
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Operation(summary = "약품 db로 가져오기")
    @GetMapping("/all") // pharma-c/medicine/all
    public void saveAllMedicines() throws IOException {
       medicineService.saveAllMedicine();
    }

    @Operation(summary = "의약품 전체조회")
    @GetMapping("/items")
    public ResponseEntity<?> getAllMedicines(Pageable pageable) {
        try{
            log.info("의약품 전체 조회하기");
            MedicineDataDTO result = medicineService.getAllMedicines(pageable);

            return ResponseEntity.ok(result);
        } catch (MedicineNotFound e) {
            return ResponseEntity.status(MedicineErrorCode.MEDICINE_NOT_FOUND.getStatus())
                    .body(MedicineErrorCode.MEDICINE_NOT_FOUND.getReason());
        }
    }

    @Operation(summary = "의약품 코드로 조회(1개)")
    @GetMapping("/items/{code}")
    public ResponseEntity<?> getMedicineByCode(@PathVariable String code) {
        try {
            log.info("의약품 코드로 1개 조회하기");
            MedicineDTO result = medicineService.getMedicineByCode(code);

            return ResponseEntity.ok(result);
        } catch (MedicineNotFound e) {
            return ResponseEntity.status(MedicineErrorCode.MEDICINE_NOT_FOUND.getStatus())
                    .body(MedicineErrorCode.MEDICINE_NOT_FOUND.getReason());
        }
    }

    @Operation(summary = "의약품 검색")
    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> getMedicineBySearch(@PathVariable String keyword, Pageable pageable) {
        try{
            log.info("검색한 의약품 조회하기");
            MedicineDataDTO result = medicineService.getMedicineBySearch(keyword, pageable);

            return ResponseEntity.ok(result);
        } catch (MedicineNotFound e) {
            return ResponseEntity.status(MedicineErrorCode.MEDICINE_NOT_FOUND.getStatus())
                    .body(MedicineErrorCode.MEDICINE_NOT_FOUND.getReason());
        }
    }

//    @Operation(summary = "의약품 검색")
//    @GetMapping("/{search}")
//    public ResponseEntity<ResponseApiDTO> getSearchedMedicines(
//            @PathVariable String search,
//            Pageable pageable) {
//        try {
//            Page<Medicine> medicines = medicineService.getSearchedMedicines(search, pageable);
//
//            PageInfoDTO pageInfo = new PageInfoDTO(
//                    medicines.getNumber() + 1,
//                    medicines.getSize(),
//                    medicines.getTotalPages(),
//                    (int) medicines.getTotalElements()
//            );
//
//            MedicineDataDTO data = new MedicineDataDTO(medicines.getContent(), pageInfo);
//
//            ResponseApiDTO response = new ResponseApiDTO(200, true, "의약품 불러오기에 성공하였습니다.", data);
//
//            return ResponseEntity.ok(response);
//        } catch (MedicineNotFound e) {
//            MedicineErrorCode errorCode = (MedicineErrorCode) e.getErrorCode();
//            ResponseApiDTO response = new ResponseApiDTO(
//                    errorCode.getStatus().value(),
//                    false,
//                    errorCode.getReason(),
//                    null
//            );
//
//            return ResponseEntity.status(errorCode.getStatus()).body(response);
//        }
//    }

}
