package PharmaC.backend.domain.User.dto.request;

import PharmaC.backend.domain.User.domain.Gender;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoRequestDTO {

    @NotNull
    @Size(max = 30)
    @Column(unique = true,name = "site_id")
    private String siteId;


    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Nullable
    private String image;

    @Nullable
    @Size(max = 300)
    private String disease;
}
