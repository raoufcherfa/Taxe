package org.sid.taxes.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth.inMemoryAuthentication().withUser("raouf").roles("ADMIN","USER").password("{noop}123");
        auth.inMemoryAuthentication().withUser("Mohamed").roles("USER").password("{noop}123");*/
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
                "select username as principal,pass as credentials,active from users where username =?"
        ).authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username =?").
                rolePrefix("ROLE_").passwordEncoder(new MessageDigestPasswordEncoder("MD5"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.formLogin();
       //http.authorizeRequests().anyRequest().hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/entreprises","/saveEntreprise","/formEntreprise").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/entreprises","/taxes").hasRole("USER");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
