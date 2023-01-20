package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> {
			try {
				authz.antMatchers("/home/**").permitAll().anyRequest().authenticated().and()
						.formLogin(
								form -> form.loginPage("/login").defaultSuccessUrl("/usuario/pedido", true).permitAll())
						.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/home")).csrf().disable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		UserDetails user = User.withDefaultPasswordEncoder().username("maria").password("maria").roles("ADM").build();
		return new InMemoryUserDetailsManager(user);
	}

	@Autowired
	private DataSource dataSource;

	@Bean
	public UserDetailsManager users(DataSource dataSource) {
//		UserDetails user = User.withDefaultPasswordEncoder().username("maria").password("maria").roles("USER").build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//		users.createUser(user);
		return users;
	}

}
