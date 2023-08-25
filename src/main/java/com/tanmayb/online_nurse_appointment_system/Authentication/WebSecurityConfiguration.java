package com.tanmayb.online_nurse_appointment_system.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    
    @Bean
    AuthenticationProvider authenticationProvider() {
        
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        
        authenticationProvider.setUserDetailsService(userDetailsService);
        
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        
        return authenticationProvider;
        
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/customer/update/*").hasAuthority("CUSTOMER")
                .requestMatchers("/api/customer/delete/*").hasAuthority("CUSTOMER")
                .requestMatchers("/api/nurse/update/*").hasAuthority("NURSE")
                .requestMatchers("/api/nurse/delete/*").hasAuthority("NURSE")
                .requestMatchers("/api/admin/*").hasAuthority("SUPERUSER")
                .requestMatchers("/api/healthcare/*").hasAuthority("ADMIN")
                .requestMatchers("/api/appointment/pay/*").hasAuthority("CUSTOMER")
                .requestMatchers("/api/appointment/update/*").hasAnyAuthority("CUSTOMER", "NURSE", "ADMIN")
                .requestMatchers("/api/appointment/delete/*").hasAuthority("CUSTOMER")
                .requestMatchers("/api/appointment/view/customer/*").hasAuthority("CUSTOMER")
                .requestMatchers("/api/appointment/view/nurse/*").hasAuthority("NURSE")
                .requestMatchers("/api/appointment/view/*").hasAuthority("ADMIN")
                .requestMatchers("/api/appointment/book").hasAuthority("CUSTOMER")
                .anyRequest().authenticated())
            .exceptionHandling(handling -> handling
                .authenticationEntryPoint(authenticationHandler))
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
        
    }
    
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        
        return (web) -> web.ignoring().requestMatchers("/api/customer/register").requestMatchers("/api/nurse/register").requestMatchers("/error");
        
    }
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationHandler authenticationHandler;
    
}