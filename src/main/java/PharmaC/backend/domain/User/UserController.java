package PharmaC.backend.domain.User;
import PharmaC.backend.domain.User.dto.request.UserSignInRequestDto;
import PharmaC.backend.domain.User.dto.request.UserSignUpRequestDto;
import PharmaC.backend.domain.User.service.UserService;
import PharmaC.backend.global.jwt.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import PharmaC.backend.global.common.dto.AwsS3Url;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Tag(name = "User")

public class UserController {

    private final UserService userService;


    @PostMapping("/join")
    public Long join(@RequestBody UserSignUpRequestDto requestDto){
        return userService.join(requestDto);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid UserSignInRequestDto requestDto){
        return userService.login(requestDto);
    }

    @Operation(summary = "회원 이미지 url 생성하기")
    @GetMapping("/image")
    public AwsS3Url getImageUrl() {
        log.info("회원 이미지 url 생성하기");
        return userService.getImageUrl();
    }
}

