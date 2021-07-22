package com.example.demo.config;

import com.example.demo.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Được quyền truy cập khi chưa login
        http.authorizeRequests().antMatchers("/login", "/signup", "/", "/images/**", "/post").permitAll();

        // Có những quyền Admin, Mod, Member sẽ được truy cập
        http.authorizeRequests().antMatchers("/user", "/comment", "/transferMoney").hasAnyAuthority("Admin", "Member", "Mod");

        //chỉ có quyền Admin, Mod mới được truy cập
        http.authorizeRequests().antMatchers( "/post/edit", "/post/add", "/admin", "/admin/adminPage", "/admin/tags/**","/admin/posts/**").hasAnyAuthority("Admin", "Mod");

        //chỉ có quyền Admin mới được truy cập
        http.authorizeRequests().antMatchers("/admin/users/**").hasAnyAuthority("Admin");

//        // Khi không đủ quyền truy cập sẽ bị chuyển hướng
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/error");

        // Custom login
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/authentication")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout");
    }

}
