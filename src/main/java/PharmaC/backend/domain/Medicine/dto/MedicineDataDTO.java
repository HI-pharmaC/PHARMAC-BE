package PharmaC.backend.domain.Medicine.dto;

import PharmaC.backend.domain.Medicine.domain.Medicine;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class MedicineDataDTO {
    private List<Medicine> content;
    private PageInfoDTO pageInfo;

    public MedicineDataDTO(List<Medicine> content, PageInfoDTO pageInfo) {
        this.content = content;
        this.pageInfo = pageInfo;
    }
}
