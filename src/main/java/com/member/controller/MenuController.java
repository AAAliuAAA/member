package com.member.controller;

import com.member.entity.Menu;
import com.member.service.MenuServiceImp;
import com.member.util.EasyUIAdapterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    private MenuServiceImp menuServiceImp;

    @RequestMapping("/menus")
    public ResponseEntity findAll(){
        Menu menu = new Menu();
        List<Menu> menus = menuServiceImp.findAll(menu);
        menus = EasyUIAdapterUtil.convertMenu(menus);
        return ResponseEntity.ok(menus);
    }
}
