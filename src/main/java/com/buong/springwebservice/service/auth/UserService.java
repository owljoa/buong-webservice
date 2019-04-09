package com.buong.springwebservice.service.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public interface UserService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String username);
}
