package PharmaC.backend.domain.History.exception;

import PharmaC.backend.global.error.BaseErrorException;

public class HistoryNotFound extends BaseErrorException {

    public static final HistoryNotFound EXCEPTION = new HistoryNotFound();

    private HistoryNotFound() {
        super(HistoryErrorCode.HISTORY_NOT_FOUND);
    }
}
