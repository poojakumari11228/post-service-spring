package com.lab.lab.service.impl;

import com.lab.lab.entity.User;
import com.lab.lab.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userDetailsService")
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public JwtUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        var userDetails = new JwtUserDetails(user.orElse(null));
        return userDetails;
    }

}
