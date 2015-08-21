package com.dcx.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by DCX-PC on 2015-08-19.
 */
public class ShiroPermissionTest {

    @Test
    public void testHasPermission(){
        login("classpath:shiro-permission.ini", "dcx", "123");
        //判断是否有某一个角色
        subject().checkPermission("user:create");

        subject().checkPermissions("user:create", "user:update");

        try {
            subject().checkPermission("user:view");
        }catch (Exception e) {
            System.out.print("没有view权限");
        }
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
