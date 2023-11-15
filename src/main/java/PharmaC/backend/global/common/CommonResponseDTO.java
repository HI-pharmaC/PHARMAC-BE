package PharmaC.backend.global.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDTO<T> {

    int status;
    boolean success;
    String message;
    T data;

    public static <T> CommonResponseDTO<T> onSuccess(int status, boolean success, String message, T data) {
        return new CommonResponseDTO<>(status, success, message, data);
    }

    public static CommonResponseDTO onSuccessWithNoData(int status, boolean success, String message) {
        return new CommonResponseDTO<>(status, success, message, null);
    }

    public static CommonResponseDTO onFailure(int status, boolean success, String message) {
        return new CommonResponseDTO<>(status, success, message, null);
    }
}
