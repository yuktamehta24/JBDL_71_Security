package org.gfg.JBDL_71_Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()

                .username("user")
//                .password("$2a$10$Jya4hVszwKSEi4fDVhyqMuwIfykIL/ker6zzFCmgQehvziP8nj5Qq")
                .password("password")
                .authorities("USER") // authorities -> USER
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .authorities("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        // only for DEMO
//        UserDetails user = User.builder()
//                .username("user")
//                .password("password")
//                .authorities("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("admin")
//                .authorities("USER", "ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//
//        if (!users.userExists(user.getUsername())) {
//            users.createUser(user);
//        }
//        if (!users.userExists(admin.getUsername())) {
//            users.createUser(admin);
//        }
//        return users;
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService());
//        authenticationProvider.setPasswordEncoder(getEncoder());
//        return authenticationProvider;
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .requestMatchers("/user").hasAuthority("USER")
                        .requestMatchers("/spring-security").hasAnyAuthority("ADMIN", "USER")
                        .anyRequest().permitAll())
                .formLogin(withDefaults()) //browser
                .httpBasic(withDefaults()) //clients
        .csrf(csrf -> csrf.disable());
        return http.build();
    }


    @Bean
    public PasswordEncoder getEncoder(){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        System.out.println("user is: " + bCryptPasswordEncoder
//                .encode("user"));
        System.out.println("password is: " + bCryptPasswordEncoder
                .encode("password"));
//        return bCryptPasswordEncoder;

        return NoOpPasswordEncoder.getInstance();
    }
}