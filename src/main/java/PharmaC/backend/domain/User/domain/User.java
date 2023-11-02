package PharmaC.backend.domain.User.domain;

import PharmaC.backend.domain.User.dto.request.UserInfoRequestDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(unique = true,name = "site_id")
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Nullable
    private String image;

    @Nullable
    @Size(max = 300)
    private String disease;

    private String refreshToken;

    // 생성자
    @Builder
    private User(Long id, String siteId, String password, String name, Gender gender,String image, String disease) {
        this.id = id;
        this.siteId = siteId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.disease = disease;
    }

    public void addUserAuthority(){
        this.role = Role.ROLE_USER;
    }
    public void update(UserInfoRequestDTO requestDto){
        if (requestDto.getSiteId() != null){
            this.siteId = requestDto.getSiteId();
        }
        if(requestDto.getGender() != null){
            this.gender = requestDto.getGender();
        }
        if(requestDto.getDisease() != null){
            this.disease = requestDto.getDisease();
        }
        if(requestDto.getImage() != null){
            this.image = requestDto.getImage();
        }
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

    @Override
    public String getUsername() {
        return siteId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // 정적 팩토리 메소드

}
