package xyz.uutech.www.opencartservice.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 *
 * @author kazaff
 * @date 2016/5/11
 */
public class CertificationInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertificationInterceptor.class);
    private static final String[] PROXY_REMOTE_IP_ADDRESS = { "X-Forwarded-For", "X-Real-IP" };

    @Value("${authToken}")
    private String tokenConfig;

    public static String getRemoteIp(HttpServletRequest request ) {
        for ( int i = 0 ; i < PROXY_REMOTE_IP_ADDRESS.length ; i++ ) {
            String ip = request.getHeader( PROXY_REMOTE_IP_ADDRESS[i] );
            if ( ip != null && ip.trim().length() > 0 ) {
                return getRemoteIpFromForward( ip.trim() );
            }
        }
        return request.getRemoteHost();
    }

    private static String getRemoteIpFromForward( String xforwardIp ) {
        int commaOffset = xforwardIp.indexOf( ',' );
        if ( commaOffset < 0 ) {
            return xforwardIp;
        }
        return xforwardIp.substring( 0 , commaOffset );
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验token
        String tokenCurrent = request.getHeader("token");
        if(tokenCurrent == null || !tokenConfig.equals(tokenCurrent)){
            LOGGER.warn("token no match from {}", getRemoteIp(request));
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
