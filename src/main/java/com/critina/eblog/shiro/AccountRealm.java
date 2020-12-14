package com.critina.eblog.shiro;

import com.critina.eblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm{

    @Autowired
    UserService userService;


    /**
    * @Description: 授权
    * @Param: [principals]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: sunzhen
    * @Date: 2020/12/12
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountProfile profile = (AccountProfile) principals.getPrimaryPrincipal();

        // 给id为6的admin赋予admin角色
        if(profile.getId() == 6) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("admin");
            return info;
        }

        return null;
    }

    /**
    * @Description: 认证
    * @Param: [token]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: sunzhen
    * @Date: 2020/12/12
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        AccountProfile profile = userService.login(usernamePasswordToken.getUsername(), String.valueOf( usernamePasswordToken.getPassword()));

        SecurityUtils.getSubject().getSession().setAttribute("profile", profile);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
        return info;
    }
}
