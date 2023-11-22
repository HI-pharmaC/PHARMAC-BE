package PharmaC.backend.domain.History.dto;

import PharmaC.backend.domain.History.domain.History;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HistoryDTO {

    private Long id;
    private String name;
    private String symptom;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean nowState;

    @Builder
    private HistoryDTO(Long id, String name, String symptom, LocalDate startDate, LocalDate endDate, Boolean nowState) {
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
