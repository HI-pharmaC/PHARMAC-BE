package PharmaC.backend.domain.History.mapper;

import PharmaC.backend.domain.History.domain.History;
import PharmaC.backend.domain.History.dto.HistoryDTO;
import PharmaC.backend.domain.History.vo.HistoryVo;
import PharmaC.backend.domain.User.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {
    public History toEntity(HistoryVo historyVo, User user) {
        return History.of(historyVo, user);
    }

    public List<HistoryDTO> toDtoList(List<History> historyList) {
        List<HistoryDTO> dtoList = new ArrayList<>();
        for (History h : historyList) {
            dtoList.add(HistoryDTO.entityToDto(h));
        }
        return dtoList;
    }

    public List<History> sortHistory(List<History> historyList) {
        Comparator<History> nowStateComparator = (h1, h2) -> Boolean.compare(h2.getNowState(), h1.getNowState());
        Comparator<History> endDateComparator = Comparator.comparing(History::getEndDate, Comparator.reverseOrder());
        Comparator<History> startDateComparator = Comparator.comparing(History::getStartDate, Comparator.reverseOrder());

        // 복용중 -> 복용 완료 일자 -> 복용 시작 일자 우선순위로 정렬
        Collections.sort(historyList, nowStateComparator.thenComparing(endDateComparator).thenComparing(startDateComparator));
        return historyList;
    }
}
