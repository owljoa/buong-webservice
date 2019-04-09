package com.buong.springwebservice.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByUsername(String username);
    Member findTopByOrderByIdDesc();
}
