package com.guangze.hermes.infrastructure.dao.configuration;

import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MysqlDruidProps {

    // todo 读取文件本地文件
    private final String url = "jdbc:mysql://39.108.132.195:3307/test1?useUnicode=true&&characterEncoding=utf-8&&useSSL=false";

    private final String username = "root";

    private final String password = "123456";

    private final String driverClassName = "com.mysql.jdbc.Driver";

    private final String initialSize = "2";
    private final String minIdle = "5";

    private final String maxIdle = "10";

    private final String maxActive = "10";

    private final String maxWait = "50";

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("druid.url",url);
        props.setProperty("druid.username",username);
        props.setProperty("druid.password",password);
        props.setProperty("druid.druidClassName",driverClassName);
        props.setProperty("druid.initialSize",initialSize);
        props.setProperty("druid.minIdle",minIdle);
        props.setProperty("druid.maxIdle",maxIdle);
        props.setProperty("druid.maxActive",maxActive);
        props.setProperty("druid.maxWait",maxWait);

        return props;
    }
}

