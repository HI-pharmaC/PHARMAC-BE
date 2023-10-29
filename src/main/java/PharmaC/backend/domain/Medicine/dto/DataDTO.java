package PharmaC.backend.domain.Medicine.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataDTO {

    private List<MedicineDTO> content;
    private PageInfoDTO pageInfo;
}
