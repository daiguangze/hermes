# hermes
本项目主要用于练习从各处学习而来的模块, 以及将新技术应用于线上项目时的demo制作

todo list:
- DDD架构练习
- SSE接口
- jvm性能监控
- kubernetes-java 接口解耦
- 一些公共组件

## SSE
对接ChatGLM, 内容源自知识星球, 以自己对DDD的理解以及某大厂DDD的实践应用 ,对内容做了部分调整


1. 程序入口, 由app层改到了interfaces层
2. session配置由app层改到了domain层,将GPT服务作为领域, 不同服务商提供的GPT服务作为其子领域
3. chat服务调用由app层请求domain层, (暂时将这个chat作为自己的业务服务,理论上作为外部服务,个人认为更应该扔到防腐层.) 问题: 包装的有点多,并且responseemitter好像不好包装

## jvm性能监控
暂时只接入了actuator,调研中ing


## kubernetes-java 接口解耦
实验室线上项目需要,思路来自于工作







## 项目启动

### nginx 启动命令

```shell

docker run \
--name Nginx \
-d \
-v /root/data/nginx/html:/usr/share/nginx/html \
-v /root/data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v /root/data/nginx/conf.d:/etc/nginx/conf.d \
-p 81:80 \
nginx

```



### 数据库地址读取
存放路径见  package com.guangze.hermes.infrastructure.configuration.MysqlDruidProps

mysql.txt文件存放内容
```text
jdbc:mysql://aaa.xxx.xxx.xxx:1111/test1?useUnicode=true&&characterEncoding=utf-8&&useSSL=false
```