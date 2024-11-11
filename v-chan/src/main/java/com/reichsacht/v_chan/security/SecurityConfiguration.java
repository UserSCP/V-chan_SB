package com.reichsacht.v_chan.security;

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


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> 
                session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)  // Mantener la sesión si es necesario
            )
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/login", "/register", "/", "/settings", "/settings/").permitAll()
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                    .requestMatchers("/users/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")  // Página de login personalizada
                    .successHandler((request, response, authentication) -> {
            	        request.getSession().setAttribute("flash.message", "¡Bienvenido, inicio de sesión exitoso!");
            	        response.sendRedirect("/");
            	    })
                    .defaultSuccessUrl("/", true)  // Redirigir a la página principal después de iniciar sesión
                    .failureUrl("/login?error=true")  // Si el login falla, redirigir a la página de error
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
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return auth.build();
    }
}
