package br.com.alura.mvc.mudi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/h2-console/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin(form -> form.loginPage("/login")
					.permitAll())
			.logout(logout -> logout.logoutUrl("/logout"));

		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource).withDefaultSchema()
			.withUser(User.withUsername("rafael")
				.password(passwordEncoder().encode("123456"))
				.roles("USER"));

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
