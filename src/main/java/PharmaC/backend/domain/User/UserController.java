package PharmaC.backend.domain.User;
import PharmaC.backend.domain.User.dto.UserDTO;
import PharmaC.backend.domain.User.dto.request.UserInfoRequestDTO;
import PharmaC.backend.domain.User.dto.request.UserPasswordDTO;
import PharmaC.backend.domain.User.dto.request.UserSignInRequestDTO;
import PharmaC.backend.domain.User.dto.request.UserSignUpRequestDTO;
import PharmaC.backend.domain.User.dto.response.UserIdDTO;
import PharmaC.backend.domain.User.service.UserService;
import PharmaC.backend.domain.User.dto.response.TokenDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import PharmaC.backend.global.common.dto.AwsS3Url;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
@Tag(name = "User")

public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입하기")
    @PostMapping("/join")
    public UserDTO join(
            @RequestBody @Valid UserSignUpRequestDTO requestDto){
        return userService.join(requestDto);
    }

    @Operation(summary = "로그인하기")
    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Valid UserSignInRequestDTO requestDto){
        return userService.login(requestDto);
    }

    @Operation(summary = "아이디 중복체크")
    @GetMapping ("/siteId")
    public UserIdDTO checkSiteId(
            @RequestParam("siteId") String id
    ){
        return userService.checkSiteId(id);
    }

    @Operation(summary = "사용자 프로필 조회")
    @GetMapping("/{id}")
    public UserDTO getInfo(
            @PathVariable("id") Long id){
        return userService.getInfo(id);
    }

    @Operation(summary = "사용자 프로필 수정")
    @PatchMapping("/{id}")
    public UserDTO updateInfo(
            @PathVariable("id") Long id,
            @RequestBody UserInfoRequestDTO requestDto){
        return userService.updateInfo(requestDto,id);
    }

    @Operation(summary = "사용자 비밀번호 재설정")
    @PatchMapping("/{id}/password")
    public UserDTO updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UserPasswordDTO passwordDTO
            ){
        return userService.updatePassword(passwordDTO,id);
    }

//    @Operation(summary = "토큰 재발급")
//    @PutMapping("/newAccess")
//    public TokenDTO issueAccessToken(HttpServletRequest request) {
//        return userService.issueAccessToken(request);
//    }

    @Operation(summary = "회원 이미지 url 생성하기")
    @GetMapping("/image")
    public AwsS3Url getImageUrl() {
        log.info("회원 이미지 url 생성하기");
        return userService.getImageUrl();
    }
}

