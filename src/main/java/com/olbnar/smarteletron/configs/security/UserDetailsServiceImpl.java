package com.olbnar.smarteletron.configs.security;

import com.olbnar.smarteletron.models.security.UserModel;
import com.olbnar.smarteletron.repositories.security.UserModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userModelRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username:" + username));
        return UserDetailsImpl.build(userModel);
    }

    public UserDetails loadUserById(UUID userId) throws AuthenticationCredentialsNotFoundException {
        UserModel userModel = userModelRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found with userId: " + userId));
        return UserDetailsImpl.build(userModel);
    }
}
