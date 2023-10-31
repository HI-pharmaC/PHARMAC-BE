package PharmaC.backend.domain.User.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private String accessToken;
    private String refreshToken;
}
