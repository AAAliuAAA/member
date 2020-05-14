package com.member.util;

import com.member.entity.Role;
import com.member.entity.Student;
import com.member.service.RoleServiceImp;
import com.member.service.StudentServiceImp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private StudentServiceImp studentServiceImp;
    @Autowired
    RoleServiceImp roleServiceImp;
    ////实现AuthorizingRealm接口用户用户认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权的方法被调用了");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //认证返回的dbstudent可以在这里获得  --SimpleAuthenticationInfo(dbstudent,dbstudent.getPwd(),"MyShiro");这个对象的第一个参数
        Student student = (Student) principalCollection.getPrimaryPrincipal();
        // 这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();

        //将查到的权限和角色分别传入authorizationInfo中
        permsSet = roleServiceImp.findAllPermissions(student.getRoleId());
        rolesSet =roleServiceImp.selectRoleByUserId(student.getId());

        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }
//认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //执行了login方法则会跳转到这个方法中
        System.out.println("认证的方法被调用了");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //如果用户名不存在，返回null时底层会抛出UnknownAccountException
        Student s = new Student();
        s.setStuNo(token.getUsername());
        Student dbstudent = studentServiceImp.findById(s);
        if (dbstudent == null){
            return null;
        }
        //判断密码,交给SimpleAuthenticationInfo对象，第一个参数代表返回login的数据，第二个表示password(数据库的)
        return new SimpleAuthenticationInfo(dbstudent,dbstudent.getPwd(),"MyShiro");
    }
}
