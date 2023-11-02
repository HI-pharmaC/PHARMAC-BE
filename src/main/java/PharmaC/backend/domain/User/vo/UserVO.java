package PharmaC.backend.domain.User.vo;

import PharmaC.backend.domain.User.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserVO {


    @Schema(defaultValue = "user1", description = "회원 아이디")
    @NotEmpty(message = "아이디를 입력해주세요")
    private String siteId;

    @Schema(defaultValue = "******", description = "회원 비밀번호")
    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{6,20}",
            message = "영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 6자 ~ 20자여야 합니다.")
    private String password;

    @Schema(defaultValue = "김홍익", description = "회원 이름")
    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @Schema(defaultValue = "F", description = "회원 성별")
    @NotEmpty(message = "성별을 선택해주세요")
    private Gender gender;

    @Schema(defaultValue = "홍익.jpg", description = "회원 프로필 사진")
    private String image;

    @Schema(defaultValue = "당뇨", description = "보유 질병")
    private String disease;

}
