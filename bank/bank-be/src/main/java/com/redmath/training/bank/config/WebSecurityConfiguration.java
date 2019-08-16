package com.redmath.training.bank.config;


import com.redmath.training.bank.user.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/h2-console/**");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/account").permitAll()
                .antMatchers(HttpMethod.GET, "/api/transactions").permitAll()

                .antMatchers(HttpMethod.GET, "/api/account/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/account/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/account/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/transactions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/transactions/amount/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/transactions/addTransaction").permitAll()
                .and()

                .csrf().disable();
        super.configure(http);
    }

}
