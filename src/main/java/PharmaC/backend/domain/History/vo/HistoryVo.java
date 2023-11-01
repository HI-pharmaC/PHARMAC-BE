package PharmaC.backend.domain.History.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;

@Getter
public class HistoryVo {

    @Schema(defaultValue = "타이레놀", description = "약 이름")
    @NotEmpty(message = "약 이름을 입력해주세요")
    private String name;

    @Schema(defaultValue = "감기", description = "증상")
    private String symptom;

    @Schema(defaultValue = "2023-10-20", description = "복용 시작 일자")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotEmpty(message = "복용 시작 날짜를 입력해주세요")
    private LocalDate startDate;

    @Schema(defaultValue = "2023-10-30", description = "복용 완료 일자")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Schema(defaultValue = "false", description = "현재 복용 여부")
    @NotNull(message = "현재 약 복용 여부를 입력해주세요")
    private Boolean nowState;

}
