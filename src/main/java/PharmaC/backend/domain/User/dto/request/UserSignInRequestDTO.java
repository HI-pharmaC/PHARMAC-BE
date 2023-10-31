package PharmaC.backend.domain.User.dto.request;

import jakarta.persistence.Column;
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
public class UserSignInRequestDTO {
    @NotNull
    @Size(max = 30)
    @Column(unique = true,name = "site_id")
    private String siteId;

    @NotNull
    @Size(max = 300)
    private String password;
}
