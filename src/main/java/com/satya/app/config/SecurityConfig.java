package com.satya.app.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.httpBasic().and().authorizeHttpRequests()
                .antMatchers("/swagger-ui/index.html").hasAnyRole("DOCTOR","PATIENT")
                .antMatchers(HttpMethod.GET,"/doctor/doctorappointment").hasAnyRole("DOCTOR")
                .antMatchers(HttpMethod.GET,"/patient/myappointment").hasAnyRole("PATIENT")
                .antMatchers(HttpMethod.POST,"/doctor/save").hasAnyRole("DOCTOR")
                .antMatchers(HttpMethod.POST,"/patient/save").hasAnyRole("PATIENT")
                .antMatchers(HttpMethod.GET,"/viewprescrption").hasAnyRole("DOCTOR","PATIENT")
                .antMatchers(HttpMethod.POST,"/saveprescrption").hasAnyRole("DOCTOR","PATIENT")
                .and().csrf().disable().headers().frameOptions().disable();

    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication()
                .withUser("patient01").password("{noop}password").roles("PATIENT").and()
                .withUser("doctor01").password("{noop}password").roles("DOCTOR").and()
                .withUser("admin01").password("{noop}password").roles("DOCTOR","PATIENT");
    }
}
