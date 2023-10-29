package PharmaC.backend.domain.History.dto.request;

import PharmaC.backend.domain.History.vo.HistoryVo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class UpdateHistoryRequestDTO {

    @JsonUnwrapped private HistoryVo historyVo;
}
