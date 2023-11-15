package PharmaC.backend.domain.History;

import PharmaC.backend.domain.History.dto.HistoryDTO;
import PharmaC.backend.domain.History.dto.request.AddHistoryRequestDTO;
import PharmaC.backend.domain.History.dto.request.UpdateHistoryRequestDTO;
import PharmaC.backend.domain.History.service.HistoryService;
import PharmaC.backend.global.common.CommonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/history")
@Tag(name = "History")
public class HistoryController {

    private final HistoryService historyService;

    @Operation(summary = "복용기록 생성하기")
    @PostMapping("/{userId}")
    public CommonResponseDTO<HistoryDTO> createHistory(
            @RequestBody AddHistoryRequestDTO addHistoryRequestDTO,
            @PathVariable(name = "userId") Long userId
    ) {
        log.info("복용기록 생성하기");
        return CommonResponseDTO.onSuccess(
                201,
                true,
                "복용기록 생성하기 성공",
                historyService.createHistory(addHistoryRequestDTO, userId)
        );
    }

    @Operation(summary = "회원 복용기록 조회")
    @GetMapping("/{userId}")
    public CommonResponseDTO<List<HistoryDTO>> getAllHistory(
            @PathVariable(name = "userId") Long userId
    ) {
        log.info("회원 복용기록 조회(최신순)하기");
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "회원 복용기록 조회 성공",
                historyService.getAllHistory(userId)
        );
    }

    @Operation(summary = "복용기록 수정")
    @PatchMapping("/{historyId}")
    public CommonResponseDTO<HistoryDTO> updateHistory(
            @PathVariable(name = "historyId") Long historyId,
            @RequestBody UpdateHistoryRequestDTO updateHistoryRequestDTO
    ) {
        log.info("복용기록 수정하기");
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "복용기록 수정하기 성공",
                historyService.updateHistory(updateHistoryRequestDTO, historyId)
        );
    }

    @Operation(summary = "복용기록 삭제")
    @DeleteMapping("/{historyId}")
    public CommonResponseDTO deleteHistory(
            @PathVariable(name = "historyId") Long historyId
    ) {
        log.info("복용기록 삭제하기");
        historyService.deleteHistory(historyId);
        return CommonResponseDTO.onSuccessWithNoData(
                200,
                true,
                "복용기록 삭제하기 성공"
        );
    }

}
