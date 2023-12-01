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