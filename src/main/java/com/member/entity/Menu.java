package com.member.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Menu {
    private Integer id;
    private String name;
    private String parentNode;
    private String node;
    private String url;
}
