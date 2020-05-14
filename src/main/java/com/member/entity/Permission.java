package com.member.entity;

import lombok.Data;

@Data
public class Permission {
    private Integer id;
    private Integer roleId;
    private String perms;
}
