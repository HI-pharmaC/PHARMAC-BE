package PharmaC.backend.domain.Medicine.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineApiResponseDTO {
        private int status;
        private boolean success;
        private String message;
        private DataDTO data;
        private PageInfoDTO pageInfo;

        // 위의 필드에 대한 게터와 세터 메서드를 만듭니다.
}
