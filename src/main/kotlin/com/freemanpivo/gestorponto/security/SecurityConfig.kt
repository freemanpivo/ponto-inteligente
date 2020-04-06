package com.freemanpivo.gestorponto.security

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration //Make this as a configuration class
@EnableWebSecurity //Turn on Web Security
class SecurityWebInitializer : WebSecurityConfigurerAdapter(){
    override fun configure(http: HttpSecurity) {
        //This tells Spring Security to authorize all requests
        //We use formLogin and httpBasic
        http
                .authorizeRequests().antMatchers("/api/lancamentos").permitAll()
                .anyRequest()
                .authenticated().and()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        //This code sets up a user store in memory. This is
        //useful for debugging and development
        auth
                .inMemoryAuthentication()
                .withUser("pedro")
                .password("{noop}pedro1234")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN")
    }
}