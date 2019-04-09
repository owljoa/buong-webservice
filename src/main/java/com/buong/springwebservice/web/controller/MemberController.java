package com.buong.springwebservice.web.controller;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.dto.member.MemberLoginRequestDto;
import com.buong.springwebservice.dto.member.MemberRegisterRequestDto;
import com.buong.springwebservice.service.auth.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
//@Api(value = "member", description = "member controller")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/registration")
    public Long registration(@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) {
        logger.info("Registration attempt -> username : " + memberRegisterRequestDto.getUsername()
                    + " password : " + memberRegisterRequestDto.getPassword()
                    + " name : " + memberRegisterRequestDto.getName());

        return memberService.register(memberRegisterRequestDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        logger.info("Login attempt -> username : " + memberLoginRequestDto.getUsername()
                + " password : " + memberLoginRequestDto.getPassword());

        Member member = (Member) userDetailsService.loadUserByUsername(memberLoginRequestDto.getUsername());

        return "welcome " + memberLoginRequestDto.getUsername();
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        logger.info("main page");
        return "welcome";
    }

}
