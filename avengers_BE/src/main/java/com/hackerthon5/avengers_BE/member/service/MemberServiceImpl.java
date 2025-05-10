package com.hackerthon5.avengers_BE.member.service;


import com.hackerthon5.avengers_BE.member.DTO.SignupRequest;
import com.hackerthon5.avengers_BE.member.domain.Member;
import com.hackerthon5.avengers_BE.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signup(SignupRequest signupRequest) {

        System.out.println(signupRequest.toString());

        Optional<Member> exitsEmail = memberRepository.findByEmail(signupRequest.getEmail());
        Optional<Member> exitsNickname = memberRepository.findByNickname(signupRequest.getNickname());

        if (exitsEmail.isPresent()) return "이미 가입되어 있는 유저 입니다." ;

        if (exitsNickname.isPresent()) return "이미 존재하는 닉네임입니다." ;

        Member member = Member.builder()
                .role("USER")
                .nickname(signupRequest.getNickname())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .registeredAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        return "회원가입을 축하드립니다. 로그인해주세요.";
    }


    @Transactional(readOnly = true)
    @Override
    public Member myInfo(User user) {

        return memberRepository.findByEmail(user.getUsername())
           .orElseThrow(() -> new UsernameNotFoundException("유저정보가 없습니다."));
    }
}
