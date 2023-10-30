package PharmaC.backend.domain.Medicine.exception;

import PharmaC.backend.global.error.BaseErrorException;

public class MedicineNotFound extends BaseErrorException {

    public static final MedicineNotFound EXCEPTION = new MedicineNotFound();

    private MedicineNotFound() {
        super(MedicineErrorCode.MEDICINE_NOT_FOUND);
    }
}
