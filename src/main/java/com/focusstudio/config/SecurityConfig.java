package com.focusstudio.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration public class SecurityConfig {
 @Bean PasswordEncoder encoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService admins(@Value("${focus.admin.user}") String user,@Value("${focus.admin.password}") String password,PasswordEncoder encoder){
 return new InMemoryUserDetailsManager(User.withUsername(user).password(encoder.encode(password)).roles("ADMIN").build());}
 @Bean SecurityFilterChain security(HttpSecurity http) throws Exception {return http.authorizeHttpRequests(a->a.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll()).formLogin(f->f.defaultSuccessUrl("/admin",true)).logout(l->l.logoutSuccessUrl("/")).build();}
}
