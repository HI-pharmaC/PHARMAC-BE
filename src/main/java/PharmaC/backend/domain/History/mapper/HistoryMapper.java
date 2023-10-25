package PharmaC.backend.domain.History.mapper;

import PharmaC.backend.domain.History.domain.History;
import PharmaC.backend.domain.History.vo.HistoryVo;
import PharmaC.backend.domain.User.domain.User;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {
    public History toEntity(HistoryVo historyVo, User user) {
        return History.of(historyVo, user);
    }
}
