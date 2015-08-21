package com.dcx.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by DCX-PC on 2015-08-19.
 */
public class ShiroRoleTest {

    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini", "dcx", "123");
        //判断是否有某一个角色
        System.out.print(subject().hasRole("admin"));
        //判断是否有一些角色，有一个不含有则返回false
        System.out.print(subject().hasAllRoles(Arrays.asList("admin", "common")));

        boolean[] hasRoles = subject().hasRoles(Arrays.asList("admin", "common", "other"));
        System.out.println(hasRoles[0]);
        System.out.println(hasRoles[1]);
        System.out.println(hasRoles[2]);
    }
    public  void login(String config,String username,String password){
        //获取SecurityManager工厂，这里通过ini文件创建
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);

        //获取SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //得到Subject，创建用户名/密码身份凭证
        Subject subject = subject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try {
            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Subject subject(){
        return  SecurityUtils.getSubject();
    }
}
