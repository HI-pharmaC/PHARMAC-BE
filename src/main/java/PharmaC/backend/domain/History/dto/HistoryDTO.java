package PharmaC.backend.domain.History.dto;

import PharmaC.backend.domain.History.domain.History;
import PharmaC.backend.domain.User.domain.User;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HistoryDTO {

    private Long id;
    private String name;
    private String symptom;
    private Date startDate;
    private Date endDate;
    private Boolean nowState;

    @Builder
    private HistoryDTO(Long id, String name, String symptom, Date startDate, Date endDate, Boolean nowState) {
        this.id = id;
        this.name = name;
        this.symptom = symptom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nowState = nowState;
    }

    public static HistoryDTO entityToDto(History history) {
        return HistoryDTO.builder()
                .id(history.getId())
                .name(history.getName())
                .symptom(history.getSymptom())
                .startDate(history.getStartDate())
                .endDate(history.getEndDate())
                .nowState(history.getNowState())
                .build();
    }
}
