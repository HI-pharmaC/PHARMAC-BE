package PharmaC.backend.domain.User.dto;

import PharmaC.backend.domain.User.domain.Gender;
import PharmaC.backend.domain.User.domain.Role;
import PharmaC.backend.domain.User.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;
    private String siteId;
    private String password;
    private String name;
    private Gender gender;
    private Role role;
    private String image;
    private String disease;

    @Builder
    private UserDTO(Long id,String siteId, String password, String name,Gender gender, Role role, String image,String disease){
        this.id = id;
        this.siteId = siteId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.role = role;
        this.image = image;
        this.disease = disease;
    }
    public static UserDTO toEntity(User user){
        return UserDTO.builder()
                .id(user.getId())
                .siteId(user.getSiteId())
                .password(user.getPassword())
                .name(user.getName())
                .gender(user.getGender())
                .role(user.getRole())
                .image(user.getImage())
                .disease(user.getDisease())
                .build();
    }
}
