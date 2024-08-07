package com.sbs.basic1.boundedContext.member.service;

import com.sbs.basic1.boundedContext.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entity.Member;
import com.sbs.basic1.boundedContext.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    public RsData tryLogin(String username, String password) {

        Member member = memberRepository.findByUserName(username);
        if(member == null){
            return RsData.of("F-2","%s는 존재하지 않는 회원입니다.".formatted(username));
        }
        if(!member.getPassword().equals(password)){
            return RsData.of("F-1","비밀번호가 일치하지 않습니다.");
        }
        else if(!username.equals(username)){
            return RsData.of("F-2","%s는 존재하지 않는 회원입니다.".formatted(username));
        }
        return RsData.of("S-1","%s 님 환영합니다.".formatted(username),member.getId());
    }
    public Member findByUserName(String username){
        return memberRepository.findByUserName(username);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }
}
