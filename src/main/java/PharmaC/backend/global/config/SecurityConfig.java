package PharmaC.backend.global.config;

import PharmaC.backend.global.jwt.*;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getDefaultCorsConfiguration());

        return source;
    }
    private CorsConfiguration getDefaultCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "https://hi-pharmac.com",
                        "https://www.hi-pharmac.com"
                )
        );
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        return configuration;
    }
    private static final String[] SwaggerPatterns = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/api-docs/**",
            "/webjars/**",
    };

    // url 수정
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/user/join").permitAll()
                        .requestMatchers("/medicine/all").permitAll()
                        .requestMatchers(SwaggerPatterns).permitAll()
                        .anyRequest().authenticated())

                .logout((logout) -> logout
                        .logoutSuccessUrl("/"));

        return http.build();
    }
        // 검토 필요

}


