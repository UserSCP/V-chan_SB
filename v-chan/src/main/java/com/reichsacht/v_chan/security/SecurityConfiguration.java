package com.reichsacht.v_chan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.reichsacht.v_chan.component.CustomAccessDeniedHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
    private final UserDetailsService userDetailsService;
    @Autowired
    private CustomAccessDeniedHandler accessDenied;
    public SecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
    

	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> 
                session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/login", "/","/register","/settings","/register/verify-email").permitAll()
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                    .requestMatchers("/users/**").hasRole("ADMIN")
                    .requestMatchers("/test**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    )
            .exceptionHandling(exceptionHandling ->
            exceptionHandling.accessDeniedHandler(accessDenied) 
        )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .successHandler((request, response, authentication) -> {
            	        request.getSession().setAttribute("flash.message", "¡Bienvenido, inicio de sesión exitoso!");
            	        response.sendRedirect("/");
            	    })
                    .defaultSuccessUrl("/", true)  
                    .failureUrl("/login?error=true")  
                    .permitAll()
            )
            .logout(logout -> 
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
        );
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
     AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }
}
