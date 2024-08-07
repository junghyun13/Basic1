package com.sbs.basic1.boundedContext.member.repository;
import com.sbs.basic1.boundedContext.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members;
    public MemberRepository(){
        members = new ArrayList<>();
        members.add(new Member("test","1231"));
        members.add(new Member("user2","1233"));
        members.add(new Member("g6","1244"));
        members.add(new Member("r5","1237"));
    }
    public Member findByUserName(String username) {
        return members.stream()
                .filter(member->member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
