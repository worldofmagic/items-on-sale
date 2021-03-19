
package com.rbc.itemsonsale.service;


import com.rbc.itemsonsale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.rbc.itemsonsale.model.User> userOptional = userRepository.findByUsername(username);
        userOptional.orElseThrow(()->{
            throw new UsernameNotFoundException("User Not Found:" + username);
        });
        com.rbc.itemsonsale.model.User user = userOptional.get();
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
