package PharmaC.backend.domain.User.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPasswordDTO {

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String beforePassword;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String afterPassword;
}
