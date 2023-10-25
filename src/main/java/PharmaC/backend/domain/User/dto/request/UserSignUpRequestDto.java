package PharmaC.backend.domain.User.dto.request;

import PharmaC.backend.domain.User.domain.Gender;
import PharmaC.backend.domain.User.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserSignUpRequestDto {
    @NotNull
    @Size(max = 30)
    @Column(unique = true)
    private String siteId;

    @NotNull
    @Size(max = 300)
    private String password;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Nullable
    private String image;

    @Nullable
    @Size(max = 300)
    private String disease;

    public User toEntity(){
        return User.builder()
                .siteId(siteId)
                .password(password)
                .name(name)
                .gender(gender)
                .image(image)
                .disease(disease)
                .build();
    }
}
