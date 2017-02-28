package com.sky.demo.web_demo_multi_tenant_separate_schema.auth.realm;

import com.sky.demo.web_demo_multi_tenant_separate_schema.auth.token.RegisterHeaderAuthToken;
import com.sky.demo.web_demo_multi_tenant_separate_schema.util.SHAUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by user on 16/9/27.
 */
public class DeviceRegisterHeaderAuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(DeviceRegisterHeaderAuthRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        logger.debug("current authentication token is {}", token);
        return token.getClass().equals(RegisterHeaderAuthToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //no authorization
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        RegisterHeaderAuthToken registerHeaderAuthToken = (RegisterHeaderAuthToken) authenticationToken;
        logger.debug("....begin to validate AuthenticationToken....");
        String timestamp = registerHeaderAuthToken.getTimestamp();
        String tokenStr = registerHeaderAuthToken.getToken();

        logger.debug("...registry request: timestamp is {},token is {}", new Object[]{timestamp, tokenStr});
        if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(tokenStr)) {
            throw new AuthenticationException("token is empty");
        }

        try {
            String deviceToken = "sky";
            logger.debug("device token is {}", deviceToken);

            String tokenCheck = SHAUtil.SHASumForString(timestamp + deviceToken);
            if (!tokenCheck.equals(tokenStr)) {
                throw new AuthenticationException("Invalid token");
            }

            //注： 如果只传 timestamp 、默认的token 是无法识别租户信息的

        } catch (Exception e) {
            logger.error("encrypt token error" + e);
            throw new AuthenticationException("encrypt token error");
        }
        return new SimpleAuthenticationInfo(registerHeaderAuthToken.getTimestamp(), registerHeaderAuthToken.getToken(), this.getName());
    }
}
