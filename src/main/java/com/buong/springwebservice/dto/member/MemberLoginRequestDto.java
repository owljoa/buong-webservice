package com.buong.springwebservice.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginRequestDto {
    private String username;
    private String password;

    @Builder
    public MemberLoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
