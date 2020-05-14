package com.member.controller;

import com.github.pagehelper.PageInfo;
import com.member.db.Student1DBService;
import com.member.entity.Student;
import com.member.entity.Student1;
import com.member.service.StudentServiceImp;
import com.member.util.EasyUIAdapterUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImp studentServiceImp;

    @RequiresRoles("superadmin")  //超级管理员可以进行添加
    @PostMapping("/student")
    public ResponseEntity insert(Student student){
        Student entity = studentServiceImp.insertEntity(student);
        return  ResponseEntity.ok(entity);
    }

    //查询学生信息
    /*@GetMapping("/students")
   public ResponseEntity findAllData(Student student){
        List<Student> students = studentServiceImp.findAll(student);
        return  ResponseEntity.ok(students);
    } */
    @RequiresPermissions("roles:list")
    //查询学生信息
    @GetMapping("/students")
   public ResponseEntity findAllData(Student student,Integer page,Integer rows){
        PageInfo<Student> pageInfo = studentServiceImp.findPageAll(student,page,rows);
        Map map = EasyUIAdapterUtil.convertDatagridMap(pageInfo);
        return  ResponseEntity.ok(map);
    }
    //查询学生信息
    @GetMapping("/student/{id}")
//    public ResponseEntity findById(@PathVariable("id") Integer id, Model model){
    public ModelAndView findById(@PathVariable("id") Integer id){
        Student student = new Student();
        student.setId(id);
        student = studentServiceImp.findByEntity(student);
        ModelAndView modelAndView = new ModelAndView("studentApply");
        modelAndView.addObject("student",student);
//        return  ResponseEntity.ok(student);
        return  modelAndView;
    }
    //删除学生信息
    @DeleteMapping("/student/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer[] ids){
         List<Student> students = studentServiceImp.delete(ids);
        return  ResponseEntity.ok(students);
    }
    //修改信息
    @PutMapping("student")
    public ResponseEntity update(Student student){
        Student entity = studentServiceImp.updateEntity(student);
        return  ResponseEntity.ok(entity);
    }
}
