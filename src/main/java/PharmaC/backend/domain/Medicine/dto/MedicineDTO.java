package PharmaC.backend.domain.Medicine.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {

    @NotNull
    private Long id;
    private String name;
    private String itemCode;
    private String company;
    private String image;
    private String effect;
    private String takeMethod;
    private String precaution;
    private String caution;
    private String interaction;
    private String sideEffect;
    private String storage;

    /*** 페이지네이션 관련 변수 추후 설정 ***/

}
