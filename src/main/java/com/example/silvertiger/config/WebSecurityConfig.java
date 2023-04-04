package com.example.silvertiger.config;

import com.example.silvertiger.jwt.JwtAuthenticationFilter;
import com.example.silvertiger.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 설정 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //csrf 적용 x
                .csrf().disable().headers().frameOptions().disable()
                .and()

                // cors 설정 사용
                .cors()
                .and()

                //세션 사용 안함
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //URL 관리
                .authorizeRequests()
                .antMatchers("/account/join","/account/login","/account/test").permitAll()
                .anyRequest().authenticated()
                .and();
        // JwtAuthenticationFilter를 먼저 적용
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","PATCH","OPTIONS","DELETE"));
        configuration.setExposedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Authorization");


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

