package PharmaC.backend.domain.User.service;
import PharmaC.backend.domain.User.domain.User;
import PharmaC.backend.domain.User.dto.UserDTO;
import PharmaC.backend.domain.User.dto.request.UserInfoRequestDTO;
import PharmaC.backend.domain.User.dto.request.UserPasswordDTO;
import PharmaC.backend.domain.User.dto.request.UserSignInRequestDTO;
import PharmaC.backend.domain.User.dto.request.UserSignUpRequestDTO;
import PharmaC.backend.domain.User.dto.response.UserIdDTO;
import PharmaC.backend.domain.User.repository.UserRepository;
import PharmaC.backend.global.jwt.TokenProvider;
import PharmaC.backend.domain.User.dto.response.TokenDTO;
import PharmaC.backend.global.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
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


    public UserDTO join(UserSignUpRequestDTO requestDto){
        if(userRepository.existsBySiteId(requestDto.getSiteId())){
            throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
        }

        User user = userRepository.save(requestDto.toEntity());
        user.addUserAuthority();
        user.passwordEncode(passwordEncoder);
        return UserDTO.toEntity(user);
    }

    @Transactional
    public TokenDTO login(UserSignInRequestDTO requestDto){
        User user = userRepository.findBySiteId(requestDto.getSiteId())
                .orElseThrow(()-> new IllegalArgumentException("가입된 아이디가 아닙니다"));
        validateMatchedPassword(requestDto.getPassword(), user.getPassword());

        String accessToken = tokenProvider.createAccessToken(user.getUsername(),user.getRole().name());
        String refreshToken = tokenProvider.createRefreshToken();

        user.updateRefreshToken(refreshToken);
        userRepository.save(user);

        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public TokenDTO issueAccessToken(HttpServletRequest request) {
        //TODO : 만료된 accessToken 과 refreshToken 을 가져옴
        String accessToken = tokenProvider.resolveAccessToken(request);
        String refreshToken = tokenProvider.resolveRefreshToken(request);

        //TODO : accessToken 이 만료되었으면
        if(tokenProvider.validateAccessToken(accessToken)) {
            log.info("access 토큰 만료됨");
            //TODO : 만약 refreshToken 이 유효하다면
            if(tokenProvider.validateRefreshToken(refreshToken)) {
                log.info("refresh Token 은 유효합니다.");

                //TODO : DB에 저장해두었던 refreshToken 을 불러오고 새로운 Access Token 을 생성하기 위함
                User user = userRepository.findBySiteId(tokenProvider.getUserSiteId(refreshToken))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

                //TODO : 만약 DB refreshToken 와 요청한 refreshToken 가 같다면
                if(refreshToken.equals(user.getRefreshToken())) {
                    //TODO : 새로운 accessToken 생성
                    accessToken = tokenProvider.createAccessToken(user.getSiteId(),user.getRole().name());
                }
                else {
                    log.info("토큰이 변조되었습니다.");
                }
            }
            else {
                log.info("Refresh Token 이 유효하지 않습니다.");
            }
        }
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public UserIdDTO checkSiteId(String siteId){
        if(userRepository.existsBySiteId(siteId)){
            throw new IllegalArgumentException("이미 가입된 아이디 입니다.");
        }
        return UserIdDTO.toEntity(siteId);
    }

    public UserDTO getInfo(String siteId){
        //User user = validateLoginStatus();
        User user = userRepository.findBySiteId(siteId)
                        .orElseThrow(()-> new IllegalArgumentException("등록된 회원이 없습니다."));
        return UserDTO.toEntity(user);
    }

    public UserDTO updateInfo(UserInfoRequestDTO requestDto, String id){
        //User user = validateLoginStatus();
        User user = userRepository.findBySiteId(id).orElseThrow(()-> new IllegalArgumentException("등록된 회원이 없습니다."));
        user.update(requestDto);
        return UserDTO.toEntity(user);
    }

    public UserDTO updatePassword(UserPasswordDTO passwordDTO, String id){
        // User user = validateLoginStatus();
        User user = userRepository.findBySiteId(id).orElseThrow(()-> new IllegalArgumentException("등록된 회원이 없습니다."));
        if (!user.matchPassword(passwordEncoder,passwordDTO.getBeforePassword())){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        user.updatePassword(passwordEncoder,passwordDTO.getAfterPassword());
        return UserDTO.toEntity(user);
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
