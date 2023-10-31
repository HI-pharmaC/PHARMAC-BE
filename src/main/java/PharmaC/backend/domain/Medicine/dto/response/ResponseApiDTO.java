package PharmaC.backend.domain.Medicine.dto.response;

import PharmaC.backend.domain.Medicine.dto.MedicineDataDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseApiDTO {

    private int status;
    private boolean success;
    private String message;
    private MedicineDataDTO data;

    public ResponseApiDTO(int status, boolean success, String message, MedicineDataDTO data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
