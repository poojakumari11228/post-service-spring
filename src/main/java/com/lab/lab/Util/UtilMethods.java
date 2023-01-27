package com.lab.lab.Util;

import com.lab.lab.entity.User;
import com.lab.lab.repo.UserRepo;
import com.lab.lab.service.impl.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UtilMethods {

    private static UserRepo userRepo;

    @Autowired
    public UtilMethods(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public static Optional<User> getCurrentUser() {
        JwtUserDetails currentUser = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepo.findByEmail(currentUser.getUsername());
        return user;
    }
}
