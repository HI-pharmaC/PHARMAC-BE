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

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
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
}
