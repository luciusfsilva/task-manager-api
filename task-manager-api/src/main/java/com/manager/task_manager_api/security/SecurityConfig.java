package com.manager.task_manager_api.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Value("${jwt.public.key}")
	private RSAPublicKey publicKey;
	
	@Value("${jwt.private.key}")
	private RSAPrivateKey privateKey;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.
				csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs -> Necessario com manipulação de cookies
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/authenticate", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
						.anyRequest().authenticated()) // Permite acesso a autenticação e documentação Swagger sem autenticação
				.httpBasic(Customizer.withDefaults()) // Faz a tentativa da primeira autenticação com HTTP Basic
				.oauth2ResourceServer(oauth2 -> oauth2
						.jwt(Customizer.withDefaults()))
				.build();
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(publicKey)
				.build();
	}
	
	@Bean
	JwtEncoder jwtEncoder() {
		var jwk = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.build();
		
		var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
