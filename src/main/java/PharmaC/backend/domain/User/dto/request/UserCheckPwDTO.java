package PharmaC.backend.domain.User.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCheckPwDTO {

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String beforePassword;
}
