package PharmaC.backend.domain.Medicine;

import PharmaC.backend.domain.Medicine.dto.MedicineDTO;
import PharmaC.backend.domain.Medicine.dto.MedicineDataDTO;
import PharmaC.backend.domain.Medicine.exception.MedicineErrorCode;
import PharmaC.backend.domain.Medicine.exception.MedicineNotFound;
import PharmaC.backend.domain.Medicine.service.MedicineService;
import PharmaC.backend.global.common.CommonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public CommonResponseDTO<MedicineDataDTO> getAllMedicines(Pageable pageable) {
        try {
            log.info("의약품 전체 조회하기");
            MedicineDataDTO result = medicineService.getAllMedicines(pageable);

            return CommonResponseDTO.onSuccess(200, true, "의약품 전체 조회 성공", result);
        } catch (MedicineNotFound e) {
            return CommonResponseDTO.onFailure(500, false, "의약품 전체 조회 실패");
        }
    }

    @Operation(summary = "의약품 코드로 조회(1개)")
    @GetMapping("/items/{code}")
    public CommonResponseDTO<MedicineDTO> getMedicineByCode(@PathVariable String code) {
        try {
            log.info("의약품 코드로 1개 조회하기");
            MedicineDTO result = medicineService.getMedicineByCode(code);

            return CommonResponseDTO.onSuccess(200, true, "의약품 코드로 조회 성공", result);
        } catch (MedicineNotFound e) {
            return CommonResponseDTO.onFailure(500, false, "의약품 코드로 조회 실패");
        }
    }

    @Operation(summary = "의약품 검색")
    @GetMapping("/search/{keyword}")
    public CommonResponseDTO<MedicineDataDTO> getMedicineBySearch(@PathVariable String keyword, Pageable pageable) {
        try{
            log.info("검색한 의약품 조회하기");
            MedicineDataDTO result = medicineService.getMedicineBySearch(keyword, pageable);

            return CommonResponseDTO.onSuccess(200, true, "의약품 검색 성공", result);
        } catch (MedicineNotFound e) {
            return CommonResponseDTO.onFailure(500, false, "의약품 검색 실패");
        }
    }

}
