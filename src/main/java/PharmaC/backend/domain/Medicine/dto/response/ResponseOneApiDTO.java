package PharmaC.backend.domain.Medicine.dto.response;

import PharmaC.backend.domain.Medicine.domain.Medicine;

public class ResponseOneApiDTO {

    private int status;
    private boolean success;
    private String message;

    private Medicine data;

    public ResponseOneApiDTO(int status, boolean success, String message, Medicine data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
