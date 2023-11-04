package PharmaC.backend.domain.User.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserIdDTO {

    private String siteId;
    private Boolean state;

    @Builder
    private UserIdDTO(String siteId,Boolean state){
        this.siteId = siteId;
        this.state = state;
    }

    public static UserIdDTO toEntity(String siteId,Boolean state){
        return UserIdDTO.builder()
                .siteId(siteId)
                .state(state)
                .build();
    }
}
