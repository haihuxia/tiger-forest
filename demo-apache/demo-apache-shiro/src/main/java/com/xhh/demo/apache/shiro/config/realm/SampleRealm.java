package com.xhh.demo.apache.shiro.config.realm;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 加载用户和权限
 *
 * @author 扶苏
 * @version 1.0.0 createTime: 9/1/16 11:00 AM
 */
@Log4j2
public class SampleRealm extends AuthorizingRealm {

    public SampleRealm() {
        log.debug("-----------SampleRealm---------");
        setName("SampleRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String name = principalCollection.fromRealm(getName()).iterator().next().toString();
        String pwd = userMap().get(name);
        if( pwd != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            String role = roleMap().get(name);
            info.addRole(role);
            List<String> list = new ArrayList<>();
            list.add(permissionMap().get(role));
            info.addStringPermissions(list);
            return info;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String pwd = userMap().get(token.getUsername());
        if( pwd != null ) {
            return new SimpleAuthenticationInfo(token.getUsername(), pwd, getName());
        } else {
            return null;
        }
    }

    private Map<String, String> userMap() {
        Map<String, String> map = new HashMap<>();
        map.put("shiro", "shiro");
        map.put("apache", "apache");
        return map;
    }

    private Map<String, String> roleMap() {
        Map<String, String> map = new HashMap<>();
        map.put("shiro", "role1");
        map.put("apache", "role2");
        return map;
    }

    private Map<String, String> permissionMap() {
        Map<String, String> map = new HashMap<>();
        map.put("role1", "p1");
        map.put("role2", "p2");
        return map;
    }
}
