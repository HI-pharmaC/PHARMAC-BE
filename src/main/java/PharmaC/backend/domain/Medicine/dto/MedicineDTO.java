package PharmaC.backend.domain.Medicine.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.w3c.dom.Text;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {

    @NotNull
    private Long id; // 고유 id
    private String name; // 약 이름 itemName
    private String itemCode; // 약품 코드 itemSeq
    private String company; // 제조회사 entpName
    //private byte[] image; // 사진 없어서 보류
    private String effect; // 효능 efcyQesitm
    private String takeMethod; // 복용법 useMethodQesitm
    private String precaution; // 주의사항 경고 atpnWarnQesitm
    private String caution; // 주의사항 atpnQesitm
    private String interaction; // 상호작용 intrcQesitm(주의해야 하는 약)
    private String sideEffect; // 부작용 seQesitm
    private String storage; // 보관법 depositMethodQesitm

    public static MedicineDTO of(Long id, String name, String itemCode, String company, String effect, String takeMethod, String precaution,
                                 String caution, String interaction, String sideEffect, String storage) {
        return new MedicineDTO(
                id, name, itemCode, company, effect, takeMethod, precaution, caution, interaction, sideEffect, storage
        );
    }

}
