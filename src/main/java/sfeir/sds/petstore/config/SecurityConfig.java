package sfeir.sds.petstore.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
					.antMatchers("/css/**", "/app/**", "/fonts/**", "/js/**", "/currentuser").permitAll()
					.antMatchers( "/login", "/home", "/partials/**", "/categories/**" ).hasRole("USER")
					.antMatchers( HttpMethod.GET, "/pets/**" ).hasRole("USER")
					.antMatchers( HttpMethod.POST, "/pets/**" ).hasRole("USER")
					.antMatchers( HttpMethod.DELETE, "/pets/**").hasRole("ADMIN")
					.anyRequest().denyAll()
					.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.failureUrl("/login-error")
					.successForwardUrl("/home")
					.and()
				.csrf().disable();
	}
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().dataSource(dataSource)
		  .usersByUsernameQuery(
		   "select username,password, enabled from users where username=?")
		  .authoritiesByUsernameQuery(
		   "select username, role from user_roles where username=?");
	}
}