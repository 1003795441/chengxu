package com.example.pms.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chengxu
 * @Description:
 * @Date: Create in 17:21 2019/5/14
 */
@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority>   grantedAuthoritys=new ArrayList<>();
        grantedAuthoritys.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                //权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
                return "ROLE_ADMIN";
            }
        });
        PasswordEncoder passwordEncoder;
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("123")
                        .password("123")
                        .roles("USER").authorities(grantedAuthoritys)
                        .build();
        return user;
    }

    public static void main(String[] args) {
        System.out.println(new StandardPasswordEncoder().encode("123"));
    }
}
