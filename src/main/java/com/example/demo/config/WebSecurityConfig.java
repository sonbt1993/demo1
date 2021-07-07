package com.example.demo.config;

import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailsService;

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Được quyền truy cập khi chưa login
        http.authorizeRequests().antMatchers("/login", "/signup", "/", "/post", "/post/**").permitAll();

        // Có những quyền Admin, Mod, Member sẽ được truy cập
        http.authorizeRequests().antMatchers("/user/*" ).hasAnyAuthority("Admin", "Member");

        //chỉ có quyền Admin, Mod mới được truy cập
        http.authorizeRequests().antMatchers("/admin").hasAnyAuthority("Admin");

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
