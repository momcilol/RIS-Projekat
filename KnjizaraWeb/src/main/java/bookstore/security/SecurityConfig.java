package bookstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin").roles("ADMIN")
//			.and()
//			.withUser("user").password("{noop}user").roles("USER")
//			.and()
//			.withUser("emplyee").password("{noop}employee").roles("EMPLOYEE");
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/auth/**", "/pretraga/**").permitAll()
			.antMatchers("/korisnik/**").hasRole("Korisnik")
			.antMatchers("/narudzbine/**").hasAnyRole("Zaposleni", "Administrator")	
			.antMatchers("/adminKatalog/**", "/adminZaposleni/**", "/knjizVec/**", "/report/**").hasRole("Administrator")
			.and()
			.formLogin()
			.loginPage("/auth/loginPage")
			.loginProcessingUrl("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/auth/pocetna");
		

	}

}
