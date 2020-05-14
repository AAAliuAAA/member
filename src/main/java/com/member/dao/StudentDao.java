package com.member.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.member.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentDao extends CommonDao<Student> {
    public Student findById(@Param("entity") Student entity);
}
