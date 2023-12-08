# hermes

## nginx 启动命令

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

## 数据库地址读取
存放路径见  package com.guangze.hermes.infrastructure.dao.configuration.MysqlDruidProps

mysql.txt文件存放内容
```text
jdbc:mysql://aaa.xxx.xxx.xxx:1111/test1?useUnicode=true&&characterEncoding=utf-8&&useSSL=false
```