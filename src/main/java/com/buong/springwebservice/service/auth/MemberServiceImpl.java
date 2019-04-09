package com.buong.springwebservice.service.auth;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.domain.member.MemberRepository;
import com.buong.springwebservice.domain.member.Role;
import com.buong.springwebservice.domain.member.Role.RoleType;
import com.buong.springwebservice.dto.member.MemberRegisterRequestDto;
import com.buong.springwebservice.web.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Long register(MemberRegisterRequestDto memberRegisterRequestDto) {
        memberRegisterRequestDto.setPassword(bCryptPasswordEncoder.encode(memberRegisterRequestDto.getPassword()));

        long idToBeRegistered;
        Member currentLastMember = memberRepository.findTopByOrderByIdDesc();
        if (currentLastMember == null)
            idToBeRegistered = 1;
        else
            idToBeRegistered = currentLastMember.getId();

        // member id 로 role 결정
        List<String> roleList = new ArrayList<>();
        Role decidedRole = Role.builder()
                                .roleName(decideRole(idToBeRegistered))
                                .username(memberRegisterRequestDto.getUsername())
                                .build();

        roleList.add(decidedRole.getRoleName().toString());

        logger.info("decided roleList: " + roleList);

        // Member 빌더로 생성
        Member newMember = memberRegisterRequestDto.toEntity(roleList);

        // member 저장 및 저장된 member의 id 반환
        return memberRepository.save(newMember).getId();
    }

    private RoleType decideRole(long memberId) {
        if (memberId <= 1) {
            return RoleType.ADMIN;
        } else if (memberId <= 2) {
            return RoleType.HEAD_COACH;
        } else if (memberId <= 3) {
            return RoleType.OWNER;
        } else {
            return RoleType.USER;
        }
    }

    @Override
    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
