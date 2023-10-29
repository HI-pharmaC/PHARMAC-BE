package PharmaC.backend.domain.History.domain;

import PharmaC.backend.domain.User.domain.Gender;
import PharmaC.backend.domain.User.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date startDate;

    @Nullable
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;

    @Nullable
    @Size(max = 300)
    private String nowState;

    // 생성자
    @Builder
    private History(Long id, User user, String name, String symptom, Date startDate, Date endDate, String nowState) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.symptom = symptom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nowState = nowState;
    }

    // 정적 메소드 팩토리
}
