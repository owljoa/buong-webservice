package com.buong.springwebservice.service.auth;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.dto.member.MemberRegisterRequestDto;

public interface MemberService {
    Long register(MemberRegisterRequestDto member);

    Member findByUsername(String username);
}
