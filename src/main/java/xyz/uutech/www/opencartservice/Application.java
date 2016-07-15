package xyz.uutech.www.opencartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用启动类
 *
 * @author chenx
 * @date 2016/4/18
 */
@SpringBootApplication
@PropertySource(value= "classpath:config.properties")
@ImportResource({"classpath:disconf.xml"})//引入disconf
public class Application {
    /**
     * 应用入口函数
     *
     * @param args 运行参数
     *
     * @author chenx
     * @date 2016/4/18
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
