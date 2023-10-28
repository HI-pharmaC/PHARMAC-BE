package PharmaC.backend.domain.User.service;
import PharmaC.backend.domain.User.domain.User;
import PharmaC.backend.domain.User.dto.request.UserSignInRequestDto;
import PharmaC.backend.domain.User.dto.request.UserSignUpRequestDto;
import PharmaC.backend.domain.User.repository.UserRepository;
import PharmaC.backend.global.jwt.TokenProvider;
import PharmaC.backend.global.jwt.dto.TokenDto;
import PharmaC.backend.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import PharmaC.backend.global.common.dto.AwsS3Url;
import PharmaC.backend.infra.s3.AwsS3UrlHandler;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AwsS3UrlHandler awsS3UrlHandler;


    public Long join(UserSignUpRequestDto requestDto){
        if(userRepository.existsBySiteId(requestDto.getSiteId())){
            throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
        }

        User user = userRepository.save(requestDto.toEntity());
        user.passwordEncode(passwordEncoder);
        return user.getId();
    }

    @Transactional
    public TokenDto login(UserSignInRequestDto requestDto){
        User user = userRepository.findBySiteId(requestDto.getSiteId())
                .orElseThrow(()-> new IllegalArgumentException("가입된 아이디가 아닙니다"));
        validateMatchedPassword(requestDto.getPassword(), user.getPassword());

        String accessToken = tokenProvider.generateAccessToken(user.getUsername());
        String refreshToken = tokenProvider.generateRefreshToken();

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean checkSiteId(String siteId){
        if(userRepository.existsBySiteId(siteId)){
            throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
        }
        return true;
    }

    private User validateLoginStatus() {
        return userRepository.findBySiteId(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("로그인을 해주세요."));
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if (!passwordEncoder.matches(validPassword, memberPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }


    @Transactional(readOnly = true)
    public AwsS3Url getImageUrl() {
        return awsS3UrlHandler.handle("user");
    }
}
