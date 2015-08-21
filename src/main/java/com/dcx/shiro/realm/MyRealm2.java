package com.dcx.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by DCX-PC on 2015-08-19.
 */
public class MyRealm2 implements Realm {
    public String getName() {
        return "MyRealm1";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

       String username = (String)token.getPrincipal();//username
       String password =  new String((char[])token.getCredentials());//password

        if (!"dcx".equals(username)){
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}

