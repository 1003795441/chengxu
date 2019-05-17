package com.example.pms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Author: chengxu
 * @Description:
 * @Date: Create in 16:09 2019/5/13
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);

           http .formLogin().successForwardUrl("/login/form").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests().antMatchers("/login/form1") .access("hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .rememberMe()
                .tokenValiditySeconds(604800)
                 .rememberMeParameter("remember-me")
                .rememberMeCookieName("workspace")
                 .and()
                 .logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/?logout=true").and()
                .csrf().disable();
    }
   /* @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("hongxf").password("123456").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
    }*/
  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }*/

}
