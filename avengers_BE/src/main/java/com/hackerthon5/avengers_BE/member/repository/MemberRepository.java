package com.hackerthon5.avengers_BE.member.repository;

import com.hackerthon5.avengers_BE.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);

    public Optional<Member> findByNickname(String nickname);

}
