package PharmaC.backend.domain.Medicine.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import PharmaC.backend.global.common.ErrorReason;
import PharmaC.backend.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MedicineErrorCode implements BaseErrorCode {

    MEDICINE_NOT_FOUND(BAD_REQUEST, "MEDICINE_404_1", "일치하는 의약품이 존재하지 않습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
