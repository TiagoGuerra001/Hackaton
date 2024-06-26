package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 // Ensure this import matches your actual UserDetailsConfig service

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                     .csrf().disable()
                        .authorizeRequests()
                        .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon" +
                                ".ico").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout");
        
            return http.build();
        }
            
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

       @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/favicon.ico", "/assets/**", "/css/**", "/img" +
                "/**", "/js**", "/admin/**", "/webjars/**", "/templates/**");
    }
}
