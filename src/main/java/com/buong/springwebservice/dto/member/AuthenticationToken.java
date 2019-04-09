package com.buong.springwebservice.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationToken {
    private String username;
    private Collection authorities;
    private String token;

    @Builder
    public AuthenticationToken(String username, Collection authorities, String token) {
        this.username = username;
        this.authorities = authorities;
        this.token = token;
    }


}
