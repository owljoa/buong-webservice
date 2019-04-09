package com.buong.springwebservice.web.controller;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.dto.member.AuthenticationToken;
import com.buong.springwebservice.dto.member.MemberLoginRequestDto;
import com.buong.springwebservice.dto.member.MemberRegisterRequestDto;
import com.buong.springwebservice.service.auth.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/member")
//@Api(value = "member", description = "member controller")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    AuthenticationManager authenticationManager;

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
    public AuthenticationToken login(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpSession session) {
        String username = memberLoginRequestDto.getUsername();
        String password = memberLoginRequestDto.getPassword();

        logger.info("Login attempt -> username : " + username
                + " password : " + password);

        // 반드시 UsernamePasswordAuthenticationToken일 필요는 없지만 Authentication 인터페이스 구현체여야함
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        logger.info("Generated Token : " + token.toString());
        // token 이용해서 인증 진행, SpringSecurity에서 설정한 인증이 적용됨(?)
        Authentication authentication = authenticationManager.authenticate(token);
        logger.info("Authentication Obj : " + authentication.toString());
        // 인증결과를 context에 set -> 서버의 SecurityContext에는 인증값이 설정됨(?)
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // session 속성값에 SecurityContext 값 넣기
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        logger.info("Session Obj : " + session.getId());

        Member member = (Member) userDetailsService.loadUserByUsername(username);

        // 인증 완료 후 session에도 세팅이 되었다면 member 조회해서 계정, 권한, session id로 토큰 만들어 리턴
        return AuthenticationToken.builder()
                .username(member.getUsername())
                .authorities(member.getAuthorities())
                .token(session.getId())
                .build();
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        logger.info("main page");
        return "welcome";
    }

}
