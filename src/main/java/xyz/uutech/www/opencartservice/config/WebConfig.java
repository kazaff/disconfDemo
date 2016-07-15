package xyz.uutech.www.opencartservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import xyz.uutech.www.opencartservice.web.interceptor.CertificationInterceptor;

/**
 * Spring Web配置类
 *
 * @author chenx
 * @date 2016/4/23
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public CertificationInterceptor certificationInterceptor() {
        return new CertificationInterceptor();
    }

    /**
     * 拦截器配置
     *
     * @param registry 注册类
     *
     * @author chenx
     * @date 2016/4/23
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //授权拦截器
        registry.addInterceptor(certificationInterceptor()).addPathPatterns("/**");
    }
}
