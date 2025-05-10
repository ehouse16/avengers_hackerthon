package com.hackerthon5.avengers_BE.member.service;

import com.hackerthon5.avengers_BE.member.DTO.SignupRequest;
import com.hackerthon5.avengers_BE.member.domain.Member;
import org.springframework.security.core.userdetails.User;


public interface MemberService {

    // 회원가입
    String signup(SignupRequest request);

   Member myInfo(User user);
}
