package com.buong.springwebservice.dto.member;

import com.buong.springwebservice.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberRegisterRequestDto {
    private String username;
    private String password;
    private String name;

    @Builder
    public MemberRegisterRequestDto(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Member toEntity(List<String> authorities) {
        return Member.builder()
                .username(username)
                .password(password)
                .name(name)
                .authorities(authorities)
                .build();
    }

}
