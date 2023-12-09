package com.guangze.hermes.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;

@Slf4j
@Component
public class MysqlDruidProps {

    private Logger logger = LoggerFactory.getLogger(MysqlDruidProps.class);

    // todo 读取文件本地文件
    private String url = "";

    private final String username = "root";

    private final String password = "123456";

    private final String driverClassName = "com.mysql.jdbc.Driver";

    private final String initialSize = "2";
    private final String minIdle = "5";

    private final String maxIdle = "10";

    private final String maxActive = "10";

    private final String maxWait = "50";


    /**
     * 隐藏数据库地址, 从本地文件读取地址
     */
    @PostConstruct
    public void init() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")){
                Scanner in = new Scanner(new File("D:\\Temp\\mysql.txt"));
                if (in.hasNextLine()){
                    String ans = in.nextLine();
                    this.url = ans;
                    logger.info("[MYSQL] url 获取成功");
                }

            }else {
                Scanner in = new Scanner(new File("/root/data/mysql/mysql.txt"));
                if (in.hasNextLine()){
                    String ans = in.nextLine();
                    this.url = ans;
                    logger.info("[MYSQL] url 获取成功");
                }
            }
        }catch (Exception e){
            logger.error("[ERROR] 数据库url 读取失败 !");
        }

    }

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

