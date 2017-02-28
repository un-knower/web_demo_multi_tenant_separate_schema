package com.sky.demo.web_demo_multi_tenant_separate_schema.auth.realm;

import com.google.common.base.Preconditions;
import com.sky.demo.web_demo_multi_tenant_separate_schema.auth.token.HeaderAuthToken;
import com.sky.demo.web_demo_multi_tenant_separate_schema.context.DBContext;
import com.sky.demo.web_demo_multi_tenant_separate_schema.dto.tenant.TenantForm;
import com.sky.demo.web_demo_multi_tenant_separate_schema.service.TenantService;
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

import javax.annotation.Resource;

/**
 * Created by user on 16/9/27.
 */
public class HeaderAuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(HeaderAuthRealm.class);

    @Resource
    private TenantService tenantService;

    @Override
    public boolean supports(AuthenticationToken token) {
        logger.debug("current authentication token is {}", token);
        return token.getClass().equals(HeaderAuthToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //no authorization
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        HeaderAuthToken headerAuthToken = (HeaderAuthToken) authenticationToken;
        logger.debug("....begin to validate AuthenticationToken....");
        String timestamp = headerAuthToken.getTimestamp();
        String tokenStr = headerAuthToken.getToken();
        String deviceId = headerAuthToken.getDeviceId();

        logger.debug("...header info request: timestamp is {},token is {},device id is {}", new Object[]{timestamp, tokenStr, deviceId});
        if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(tokenStr) || StringUtils.isBlank(deviceId)) {
            throw new AuthenticationException("token is empty");
        }

        try {
            TenantForm tenant = tenantService.queryByDeviceId(deviceId);
            Preconditions.checkNotNull(tenant, "tenant is null");

            String deviceToken = tenant.getDeviceToken();
            logger.debug("device token is {}", deviceToken);

            String tokenCheck = SHAUtil.SHASumForString(timestamp + deviceToken + deviceId);
            logger.debug("begin to validate, encrypt token is {}, auth token is {}", tokenCheck, tokenStr);
            if (!tokenCheck.equals(tokenStr)) {
                throw new AuthenticationException("Invalid token");
            }

            //init tenant info
//            DBContext.initResourcesByDbKey(tenant.getSchemaName());
            DBContext.initResourcesByDeviceId(tenant.getDeviceId());
//            DBContext.initResourcesByDeviceToken(tenant.getDeviceToken());

        } catch (AuthenticationException e) {
            logger.error("encrypt token error" + e);
            throw new AuthenticationException("encrypt token error");
        } catch (Exception e) {
            logger.error("some error happen when check token" + e);
            throw new AuthenticationException("check token error");
        }

        return new SimpleAuthenticationInfo(headerAuthToken.getTimestamp(), headerAuthToken.getToken(), this.getName());
    }
}
