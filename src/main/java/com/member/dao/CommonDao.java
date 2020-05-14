package com.member.dao;

import com.github.pagehelper.PageInfo;
import com.member.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonDao<T>  {
    public List<T> findAll(@Param("entity")T entity);
    public T findByEntity(@Param("entity")T entity);
    public void insert(@Param("entity")T entity);
    public void update(@Param("entity")T entity);
    public void delete(@Param("entity")T entity);
    PageInfo<T> findPageAll(@Param("entity")T entity, Integer pageNo, Integer pageSize);
}
