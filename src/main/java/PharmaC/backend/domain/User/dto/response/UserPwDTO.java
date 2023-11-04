package PharmaC.backend.domain.User.dto.response;

import lombok.Builder;
import lombok.Getter;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Getter
public class UserPwDTO {

    private String pw;
    private Boolean state;

    @Builder
    private UserPwDTO(String pw,Boolean state){
        this.pw = pw;
        this.state = state;
    }

    public static UserPwDTO toEntity(String pw,Boolean state){
        return UserPwDTO.builder()
                .pw(pw)
                .state(state)
                .build();
    }
}
