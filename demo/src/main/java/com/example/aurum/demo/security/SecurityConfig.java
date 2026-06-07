package com.example.aurum.demo.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
private final JwtService jwtService;
private final UserRepository userRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/oauth2/**",
                                "/login/**").permitAll().anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            String acceptHeader = request.getHeader("Accept");
                            String requestedWith = request.getHeader("X-Requested-With");
                            if ("XMLHttpRequest".equals(requestedWith) ||
                                    (acceptHeader != null && acceptHeader.contains("application/json"))) {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"error\": \"Unauthorized\"}");
                            } else {
                                response.sendRedirect("/oauth2/authorization/google");
                            }
                        })
                )
                .oauth2Login(oauth -> oauth
        .successHandler((request, response, authentication) -> {

            OAuth2User user =
                    (OAuth2User) authentication.getPrincipal();

            String email = user.getAttribute("email");

            User existing = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Account not found. Please signup manually first."
                            ));

            String token =
                    jwtService.generateToken(existing.getEmail());

            response.sendRedirect(
                    "http://localhost:4200/auth/google-success?token="
                            + token
            );
        })
)
            return http.build();
    }
}
