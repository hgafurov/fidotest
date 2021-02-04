package uz.hg.fidotest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import uz.hg.fidotest.security.jwt.JWTConfigurer;
import uz.hg.fidotest.security.jwt.TokenProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private final TokenProvider tokenProvider;

	@Autowired
	public SecurityConfiguration(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/api/v1/auth/login").permitAll()
			.antMatchers("/api/v1/auth/create-user").permitAll()
			.antMatchers("/api/v1/upload").permitAll() // vaqtincha
			.antMatchers("/api/v1/download").permitAll() // vaqtincha
			.antMatchers("/api/v1/doc/save-doc").permitAll() // vaqtincha
			.antMatchers("/api/v1/doc/get-all").permitAll() // vaqtincha
			.antMatchers("/api/v1/doc/task2").permitAll() // vaqtincha
			.antMatchers("/api/v1/doc/task3").permitAll() // vaqtincha
			.antMatchers("/api/v1/doc/task4").permitAll() // vaqtincha
			.antMatchers("/api/v1/admin/").hasRole("Admin")
			.anyRequest().authenticated()
			.and()
			.apply(new JWTConfigurer(tokenProvider));
	}
		
}
