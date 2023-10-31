package PharmaC.backend.global.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String secretKey;
    private long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 30; //30분

    private long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7; //일주일

    private final CustomUserDetailService customUserDetailService;
    public static final String HEADER_ACCESS_TOKEN = "X-ACCESS-TOKEN";
    public static final String HEADER_REFRESH_TOKEN = "X-REFRESH-TOKEN";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(String siteId,String role) {
        System.out.println("로그인 siteId = " + siteId);
        Claims claims = Jwts.claims().setSubject(siteId);

        claims.put("role", role);
        System.out.println("claims = " + claims);
        Date now = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        System.out.println("token = " + token);
        return token;
    }

    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(getUserSiteId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserSiteId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        System.out.println("request = " + request.getHeaderNames());;;
        return request.getHeader(HEADER_ACCESS_TOKEN);
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader(HEADER_REFRESH_TOKEN);
    }

    public boolean validateAccessToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken);
            return claims.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}