package com.example.pms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * @Author: chengxu
 * @Description:
 * @Date: Create in 16:09 2019/5/13
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * @param http
     * @throws Exception
     * @description POST方法被排除在外了，也就是说只有GET|HEAD|TRACE|OPTIONS这4类方法会被放行，其它Method的http请求，都要验证_csrf的token是否正确，而通常post方式调用rest接口服务时，又没有_csrf的token，所以会导致我们的rest接口调用失败
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO Auto-generated method stub
        //super.configure(http);

           http .formLogin().loginPage("/login").successForwardUrl("/login/form").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .authorizeRequests().antMatchers("/login/form1") .access("hasRole('ROLE_ADMIN')")
                   .antMatchers("/me").hasAnyRole("MEMBER","ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                 .logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/?logout=true").and()
                   .rememberMe()
                   .tokenValiditySeconds(604800)
                   .userDetailsService(userDetailsService)
                   .tokenRepository(new InMemoryTokenRepositoryImpl())
                   .rememberMeParameter("remember-me")
                   .rememberMeCookieName("workspace")
                   .and().csrf().disable();

    }

    /**
     * @param auth
     * @throws Exception
     * @description 开启remberMe功能时必须要配，否则会报错UserDetailsService is required
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
