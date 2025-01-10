package com.cp.im.infrastructure.db;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DataSourceConfig
 * @Description 配置多数据源bean，默认使用master数据源
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.chat")
    public DataSource chatDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource chatDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(8);
        targetDataSources.put(DataSourceType.CHAT.name(), chatDataSource);
        return new DynamicDataSource(chatDataSource, targetDataSources);
    }
}
