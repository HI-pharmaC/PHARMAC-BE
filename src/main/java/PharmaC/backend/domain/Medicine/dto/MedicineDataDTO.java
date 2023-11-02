package PharmaC.backend.domain.Medicine.dto;

import PharmaC.backend.domain.Medicine.domain.Medicine;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Setter
public class MedicineDataDTO {
    private Page<Medicine> data;
    private PageInfoDTO pageInfo;

    public MedicineDataDTO(Page<Medicine> data, PageInfoDTO pageInfo) {
        this.data = data;
        this.pageInfo = pageInfo;
    }

    public static MedicineDataDTO of(Page<Medicine> data, PageInfoDTO pageInfo) {
        return new MedicineDataDTO(
                data, pageInfo
        );
    }
}
