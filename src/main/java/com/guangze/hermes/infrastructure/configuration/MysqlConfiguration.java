package com.guangze.hermes.infrastructure.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
@MapperScan (basePackages = "com.guangze.hermes.infrastructure.**.mapper")
public class MysqlConfiguration {

    @Primary
    @Bean("mysql")
    @Resource
    public DataSource dataSource(MysqlDruidProps mysqlDruidProps) throws SQLException{
        DruidDataSource mysql = new DruidDataSource();
        mysql.setConnectProperties(mysqlDruidProps.getProperties());
        mysql.init();
        return mysql;
    }
}
