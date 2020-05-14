package com.member.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.member.dao.StudentDao;
import com.member.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class StudentServiceImp implements StudentDao {
    @Autowired
    private StudentDao studentDao;
    @Override
    public List<Student> findAll(Student entity) {
        return studentDao.findAll(entity);
    }

    @Override
    public Student findByEntity(Student entity) {
        return studentDao.findByEntity(entity);
    }

    @Override
    public void insert(Student entity) {
        //默认未申请状态
         entity.setStu_state("1");
         //默认没有发放
        entity.setCertificate("0");
        //默认为管理员
        entity.setRoleId(1);
         studentDao.insert(entity);
    }
    public Student insertEntity(Student entity) {
        insert(entity);
        // 生成四位随机数字作为stuno
        Random random = new Random();
        Integer integer =random.nextInt(9999);
        String idno = integer.toString()+entity.getId();
        entity.setStuNo(idno);
        return updateEntity(entity);
    }
    @Override
    public void update(Student entity) {
       studentDao.update(entity);
    }

    @Override
    public void delete(Student entity) {
        studentDao.delete(entity);
    }
    public Student deleteByEntity(Student entity) {
        Student student = findByEntity(entity);
        delete(entity);
        return student;
    }
    public List<Student> delete(Integer[] ids) {
        List<Student> list = new ArrayList<>();
        if (ids!=null && ids.length>0){
            for (Integer id:ids){
                Student student = new Student();
                student.setId(id);
                student = deleteByEntity(student);
                list.add(student);
            }
        }
        return list;
    }
    public Student updateEntity(Student entity) {
         update(entity);
        return findByEntity(entity);
    }

    @Override
    public PageInfo<Student> findPageAll(Student student,Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Student> students = studentDao.findAll(student);
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        return pageInfo;
    }

    //精确查询
    @Override
    public Student findById(Student entity) {
        return studentDao.findById(entity);
    }
}
