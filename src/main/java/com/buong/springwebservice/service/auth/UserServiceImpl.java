package com.buong.springwebservice.service.auth;

import com.buong.springwebservice.domain.member.Member;
import com.buong.springwebservice.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(memberName);
        if (member == null) throw new UsernameNotFoundException(memberName);

        return member;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities = memberRepository.findByUsername(username).getAuthorities();

        return new ArrayList<>(authorities);
    }
}
