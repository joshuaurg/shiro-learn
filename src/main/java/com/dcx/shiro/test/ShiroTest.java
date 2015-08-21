package com.dcx.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by DCX-PC on 2015-08-19.
 */
public class ShiroTest {

    @Test
    public void testShiroHello(){

        //获取SecurityManager工厂，这里通过ini文件创建
        //Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //获取SecurityManager实例，并绑定到SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //得到Subject，创建用户名/密码身份凭证
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("dcx","123");

        try {

            subject.login(token);
        }catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }
}
