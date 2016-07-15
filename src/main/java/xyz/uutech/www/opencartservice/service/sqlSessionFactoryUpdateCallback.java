package xyz.uutech.www.opencartservice.service;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;
import xyz.uutech.www.opencartservice.config.DBConfig;
import xyz.uutech.www.opencartservice.util.DisconfDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 2016/7/15.
 */
@Service
@DisconfUpdateService(classes = {DBConfig.class})
public class sqlSessionFactoryUpdateCallback implements IDisconfUpdate {

    @Autowired
    private DataSource dds;

    @Autowired
    private DBConfig dbConfig;

    @Override
    public void reload() throws Exception{
        DisconfDataSource targetDds =((DisconfDataSource) dds);

        //根据更新后的配置重建数据源
        DataSource dataSource = DataSourceBuilder.create()
                .url(dbConfig.getUrl())
                .username(dbConfig.getUsername())
                .password(dbConfig.getPassword())
                .driverClassName(dbConfig.getDriverClassName())
                .build();
        Map<Object, Object> dss = new HashMap<>();
        dss.put("TARGET", dataSource);
        targetDds.setTargetDataSources(dss);
        targetDds.afterPropertiesSet();
    }
}
