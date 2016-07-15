package xyz.uutech.www.opencartservice.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.uutech.www.opencartservice.util.DisconfDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis Spring 配置类
 *
 * @author chenx
 * @date 2016/4/19
 */
@Configuration
public class MybatisSpringConfig {

    @Bean(name = "dbConfig")
    public DBConfig dbConfig(){
        return new DBConfig();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(DBConfig dbConfig){
        DataSource ds =  DataSourceBuilder.create()
                .url(dbConfig.getUrl())
                .username(dbConfig.getUsername())
                .password(dbConfig.getPassword())
                .driverClassName(dbConfig.getDriverClassName())
                .build();

        Map<Object, Object> dss = new HashMap<>();
        dss.put("TARGET", ds);

        DisconfDataSource dds = new DisconfDataSource();
        dds.setTargetDataSources(dss);

        return dds;
    }

    /**
     * Mybatis Session工厂类
     *
     * @return SqlSessionFactoryBean
     *
     * @author chenx
     * @date 2016/4/19
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("xyz.uutech.www.opencartservice.model");

        PageHelper pageHelperPlugin = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        pageHelperPlugin.setProperties(properties);

        Interceptor[] plugins = new Interceptor[] {pageHelperPlugin};
        factoryBean.setPlugins(plugins);

        return factoryBean;
    }

    /**
     * 映射扫描配置类
     *
     * @return MapperScannerConfigurer
     *
     * @author chenx
     * @date 2016/4/19
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("xyz.uutech.www.opencartservice.repository");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }
}
