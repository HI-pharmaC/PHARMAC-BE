package PharmaC.backend.domain.User.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    // 생성자
    @Builder
    private User(Long id, String siteId, String password, String name, Gender gender, String image, String disease) {
        this.id = id;
        this.siteId = siteId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.image = image;
        this.disease = disease;
    }

    // 정적 팩토리 메소드

}
