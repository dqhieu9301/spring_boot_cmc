package spring_boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import spring_boot.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired(required=true)
    private UserDetailService userDetailService;
	
    @Bean
    public JwtFilter JwtFilter() {
        return new JwtFilter();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> 
					auth.requestMatchers("/user/**").permitAll()
					.anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(JwtFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();

	}
}
