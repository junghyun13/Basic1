package com.sbs.basic1.boundedContext.member.controller;

import com.sbs.basic1.boundedContext.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entity.Member;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
public class MemberController {
    private final MemberService memberService;
    @Autowired //생성자를 주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password, HttpServletResponse resp){
        if(username == null || username.trim().length() == 0 ){
            return RsData.of("F-3","username을 입력해주세요");
        }
        if(password == null || password.trim().length() == 0 ){
            return RsData.of("F-4","password를 입력해주세요");
        }
        RsData rsData = memberService.tryLogin(username,password);
        if(rsData.isSuccess()){
            long memberId =(long) rsData.getData();
            resp.addCookie(new Cookie("loginedMemberId",memberId + ""));
        }
        return rsData;
    }
    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp){
        if(req.getCookies() != null){
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .forEach(cookie ->{
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });

        }
        return RsData.of("S-1","로그아웃을 되었습니다.");
    }

    @GetMapping("/member/me")
    @ResponseBody
    public RsData showMe(HttpServletRequest req){
        
        long loginedMemberId =0;
        if(req.getCookies() != null){
            loginedMemberId = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .map(Cookie::getValue)
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(0);

        }
        boolean isLogined = loginedMemberId >0;
        
        if(isLogined == false){
            return RsData.of("F-1","로그인 후 이용해주세요.");
        }
        Member member = memberService.findById(loginedMemberId);
        return RsData.of("S-1","당신의 username은 %s입니다.".formatted(member.getUsername()));
    }
    /*public RsData login(String username, String password){
        if(username == null || username.trim().length() == 0 ){
            return RsData.of("F-3","username을 입력해주세요");
        }
        if(password == null || password.trim().length() == 0 ){
            return RsData.of("F-4","password를 입력해주세요");
        }
        return memberService.tryLogin(username,password);
    }*/

}
