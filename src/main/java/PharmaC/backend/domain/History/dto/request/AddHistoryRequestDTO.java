package PharmaC.backend.domain.History.dto.request;

import PharmaC.backend.domain.History.vo.HistoryVo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddHistoryRequestDTO {

    @JsonUnwrapped private HistoryVo historyVo;

}
