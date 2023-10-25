package PharmaC.backend.domain.History.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import PharmaC.backend.global.common.ErrorReason;
import PharmaC.backend.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HistoryErrorCode implements BaseErrorCode {

    HISTORY_NOT_FOUND(BAD_REQUEST, "HISTORY_404_1", "해당 복약 내용이 존재하지 않습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
