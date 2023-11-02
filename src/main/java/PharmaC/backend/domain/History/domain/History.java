package PharmaC.backend.domain.History.domain;

import PharmaC.backend.domain.History.dto.request.UpdateHistoryRequestDTO;
import PharmaC.backend.domain.History.vo.HistoryVo;
import PharmaC.backend.domain.User.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // Cascade
    @JsonBackReference
    @JoinColumn(name="userId", nullable = false)
    private User user;

    @NotNull
    @Size(max = 50)
    private String name;

    @Nullable
    @Size(max = 300)
    private String symptom;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull
    private Boolean nowState;

    // 생성자
    @Builder
    private History(Long id, User user, String name, String symptom, LocalDate startDate, LocalDate endDate, Boolean nowState) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.symptom = symptom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nowState = nowState;
    }

    // 정적 메소드 팩토리
    public static History of(HistoryVo historyVo, User user) {
        return History.builder()
                .user(user)
                .name(historyVo.getName())
                .symptom(historyVo.getSymptom())
                .startDate(historyVo.getStartDate())
                .endDate(historyVo.getEndDate())
                .nowState(historyVo.getNowState())
                .build();
    }

    public void update(UpdateHistoryRequestDTO request) {
        HistoryVo historyInfo = request.getHistoryVo();
        if (historyInfo.getName() != null) {
            this.name = historyInfo.getName();
        }
        if (historyInfo.getSymptom() != null) {
            this.symptom = historyInfo.getSymptom();
        }
        if (historyInfo.getStartDate() != null) {
            this.startDate = historyInfo.getStartDate();
        }
        if (historyInfo.getEndDate() != null) {
            this.endDate = historyInfo.getEndDate();
        }
        if (historyInfo.getNowState() != null) {
            this.nowState = historyInfo.getNowState();
            if (nowState == true) {
                endDate = LocalDate.now();
            }
        }
    }
}
