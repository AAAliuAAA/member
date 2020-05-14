package com.member.service;

import com.member.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImp implements RoleDao {
    @Autowired
    private RoleDao roleDao;
    @Override
    public Set<String> selectRoleByUserId(Integer userid) {
        return roleDao.selectRoleByUserId(userid);
    }

    @Override
    public Set<String> findAllPermissions(Integer roleid) {
        return roleDao.findAllPermissions(roleid);
    }
}
