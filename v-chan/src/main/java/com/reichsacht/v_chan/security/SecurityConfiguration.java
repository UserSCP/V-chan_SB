package com.reichsacht.v_chan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/login", "/register", "/", "/settings").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() 
        )
        .formLogin(formLogin -> formLogin
        	    .loginPage("/login")
        	    .successHandler((request, response, authentication) -> {
        	        request.getSession().setAttribute("flash.message", "¡Bienvenido, inicio de sesión exitoso!");
        	        response.sendRedirect("/");
        	    })
        	    .failureUrl("/login?error=true")
        	    .permitAll()
        	)

        .logout(logout ->
            logout
                .logoutUrl("/logout")  // Ruta del logout
                .logoutSuccessUrl("/login?logout")  // Redirigir al login después del logout
//                .invalidateHttpSession(true)  
//                .clearAuthentication(true) 
        );
    return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }
}
