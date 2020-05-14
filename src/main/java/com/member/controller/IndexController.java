package com.member.controller;

import com.member.entity.Student;
import com.member.service.StudentServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class IndexController {
    @Autowired
    private StudentServiceImp studentServiceImp;
    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @PostMapping("/login1")
    public String login1(Student student,Model model){
        Student dbStudent = studentServiceImp.findById(student);
        if (dbStudent!=null && student.getPwd().equals(dbStudent.getPwd()) && student.getStuNo().equals(dbStudent.getStuNo())){
             model.addAttribute("student",dbStudent);
            return "index";
        }
        return "login";
    }

    /**
     * 登陆逻辑
     * @param student
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(Student student,Model model){
       //1.获得subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(student.getStuNo(),student.getPwd());
        //3.执行用户登陆方法
        try {
            subject.login(token);
//            model.addAttribute("student",student.getStuNo()+"");
            //登陆成功
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            System.out.println("密码错误");
        }
        return "login";
    }
    @GetMapping("/quit")
    public String quit(){
        return "login";
    }
    /*2.@RequiresRoles修饰controller层接口(类代理)
如不生效，请查看是否使用this调用了controller层方法。*/

    public String unAuth(){
        return "unAuth";
    }

}
