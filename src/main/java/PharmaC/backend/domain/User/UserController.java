package PharmaC.backend.domain.User;
import PharmaC.backend.domain.User.dto.UserDTO;
import PharmaC.backend.domain.User.dto.request.*;
import PharmaC.backend.domain.User.dto.response.UserIdDTO;
import PharmaC.backend.domain.User.dto.response.UserPwDTO;
import PharmaC.backend.domain.User.service.UserService;
import PharmaC.backend.domain.User.dto.response.TokenDTO;
import PharmaC.backend.global.common.CommonResponseDTO;
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
    public CommonResponseDTO<UserDTO> join(
            @RequestBody @Valid UserSignUpRequestDTO requestDto) {
        return CommonResponseDTO.onSuccess(
                201,
                true,
                "회원가입 성공",
                userService.join(requestDto)
        );
    }

    @Operation(summary = "로그인하기")
    @PostMapping("/login")
    public CommonResponseDTO<TokenDTO> login(@RequestBody @Valid UserSignInRequestDTO requestDto){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "로그인 성공",
                userService.login(requestDto)
        );
    }

    @Operation(summary = "아이디 중복체크")
    @GetMapping ("/siteId")
    public CommonResponseDTO<UserIdDTO> checkSiteId(
            @RequestParam("siteId") String id
    ){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "사용 가능한 아이디입니다.",
                userService.checkSiteId(id)
        );
    }

    @Operation(summary = "사용자 프로필 조회")
    @GetMapping("/{id}")
    public CommonResponseDTO<UserDTO> getInfo(
            @PathVariable("id") Long id){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "사용자 프로필 조회 성공",
                userService.getInfo(id)
        );
    }

    @Operation(summary = "사용자 프로필 수정")
    @PatchMapping("/{id}")
    public CommonResponseDTO<UserDTO> updateInfo(
            @PathVariable("id") Long id,
            @RequestBody UserInfoRequestDTO requestDto){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "사용자 프로필 수정 성공",
                userService.updateInfo(requestDto,id)
        );
    }

    @Operation(summary = "사용자 비밀번호 재설정")
    @PatchMapping("/{id}/password")
    public CommonResponseDTO<UserDTO> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UserPasswordDTO passwordDTO
            ){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "사용자 비밀번호 재설정 성공",
                userService.updatePassword(passwordDTO,id)
        );
    }

    @Operation(summary = "사용자 현재 비밀번호 확인")
    @GetMapping("/{id}/password")
    public CommonResponseDTO<UserPwDTO> checkPassword(
            @PathVariable("id") Long id,
            @RequestBody UserCheckPwDTO checkPwDTO
            ){
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "비밀번호 확인 성공",
                userService.checkPw(checkPwDTO,id)
        );
    }

//    @Operation(summary = "토큰 재발급")
//    @PutMapping("/newAccess")
//    public TokenDTO issueAccessToken(HttpServletRequest request) {
//        return userService.issueAccessToken(request);
//    }

    @Operation(summary = "회원 이미지 url 생성하기")
    @GetMapping("/image")
    public CommonResponseDTO<AwsS3Url> getImageUrl() {
        log.info("회원 이미지 url 생성하기");
        return CommonResponseDTO.onSuccess(
                200,
                true,
                "회원 이미지 url 생성하기 성공",
                userService.getImageUrl()
        );
    }
}

