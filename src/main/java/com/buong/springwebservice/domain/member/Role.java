package com.buong.springwebservice.domain.member;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role implements GrantedAuthority, Serializable {
    public enum RoleType{USER, ADMIN, OWNER, HEAD_COACH}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleName = RoleType.USER;

    @Builder
    public Role(String username, RoleType roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
