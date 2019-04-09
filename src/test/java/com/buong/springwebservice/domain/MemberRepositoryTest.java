package com.buong.springwebservice.domain;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.domain.member.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertTest() {
//        for (int i = 0; i < 100; i++) {
//            Member member;
//            Role role;
//
//            if (i <= 80) {
//                role = Role.builder().roleName("BASIC").build();
//            } else if (i <= 90) {
//                role = Role.builder().roleName("MANAGER").build();
//            } else {
//                role = Role.builder().roleName("ADMIN").build();
//            }
//
//            member = Member.builder()
//                    .uid("user" + i)
//                    .pw("user" + i + "password")
//                    .email("user" + i + "@udmtek.com")
//                    .roles(Arrays.asList(role))
//                    .build();
//
//            memberRepository.save(member);
//        }
    }

    @Test
    public void testMember() {
        Optional<Member> result = memberRepository.findById(85L);
        result.ifPresent(member -> log.info("member " + member));
    }
}
