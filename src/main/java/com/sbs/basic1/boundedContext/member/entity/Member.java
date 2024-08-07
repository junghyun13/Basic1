package com.sbs.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
    private static int lastId;
    private final Long id;
    private final String username;
    private final String password;
    public Member(String username,String password){
        this((long) ++lastId,username,password);
    }
}
