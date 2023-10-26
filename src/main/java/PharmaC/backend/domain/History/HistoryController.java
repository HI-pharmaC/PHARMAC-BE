package PharmaC.backend.domain.History;

import PharmaC.backend.domain.History.dto.HistoryDTO;
import PharmaC.backend.domain.History.dto.request.AddHistoryRequestDTO;
import PharmaC.backend.domain.History.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(summary = "회원 복용기록 생성하기")
    @PostMapping("/{userId}")
    public HistoryDTO createHistory(
            @RequestBody AddHistoryRequestDTO addHistoryRequestDTO,
            @PathVariable(name = "userId") Long userId
    ) {
        log.info("회원 복용기록 생성하기");
        return historyService.createHistory(addHistoryRequestDTO, userId);
    }

    @Operation(summary = "회원 복용기록 조회")
    @GetMapping("/{userId}")
    public List<HistoryDTO> getAllHistory(
            @PathVariable(name = "userId") Long userId
    ) {
        log.info("회원 복용기록 조회(최신순)");
        return historyService.getAllHistory(userId);
    }

}
