package com.member.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleDao {
    Set<String> selectRoleByUserId(@Param("userid") Integer userid);
    Set<String> findAllPermissions(@Param("roleid") Integer roleid);
}
