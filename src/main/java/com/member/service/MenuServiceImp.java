package com.member.service;

import com.github.pagehelper.PageInfo;
import com.member.dao.MenuDao;
import com.member.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MenuServiceImp implements MenuDao {
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Menu> findAll(Menu menu) {
        return menuDao.findAll(menu);
    }

    @Override
    public Menu findByEntity(Menu entity) {
        return null;
    }

    @Override
    public void insert(Menu entity) {
        menuDao.insert(entity);
    }

    @Override
    public void update(Menu entity) {
    }

    @Override
    public void delete(Menu entity) {

    }

    @Override
    public PageInfo<Menu> findPageAll(Menu entity, Integer pageNo, Integer pageSize) {
        return null;
    }
}
