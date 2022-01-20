package com.tma.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.security.sasl.AuthorizeCallback;

@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " was not found!!!");
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(String.valueOf(user.getRoles()))
        );
    }
}
