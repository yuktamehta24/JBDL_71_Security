package org.gfg.JBDL_71_Security.service;

import org.gfg.JBDL_71_Security.model.MyUser;
import org.gfg.JBDL_71_Security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Implement UserDetails

        MyUser myUser =  myUserRepository.findByUsername(username);

        if (myUser != null) {
            return myUser;
        }
        throw new UsernameNotFoundException(username.concat(" not found in repository"));

        // Mapped MyUser into UserDetails
//        MyUser myUser =  myUserRepository.findByUsername(username);
//
//        List<GrantedAuthority> grantedAuthorities =
//                Arrays.stream(myUser.getAuthorities().split(","))
//                        .map(authority -> new SimpleGrantedAuthority(authority))
//                        .collect(Collectors.toList());
//
//        return new User(myUser.getUsername(), myUser.getPassword(),grantedAuthorities);


        // Demo-> Hardcoded
//        return new User("abc", "abc",
//                Collections
//                        .singletonList(new SimpleGrantedAuthority("ADMIN")));
    }
}
