package PharmaC.backend.domain.User.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserIdDTO {

    private String siteId;

    @Builder
    private UserIdDTO(String siteId){
        this.siteId = siteId;
    }

    public static UserIdDTO toEntity(String siteId){
        return UserIdDTO.builder()
                .siteId(siteId)
                .build();
    }
}
