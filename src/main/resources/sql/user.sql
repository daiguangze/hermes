create table `user` (
    `id` bitint(20) not null AUTO_INCREMENT comment '自增主键',
    `account` varchar(32) not null comment '账号',
    `password` varchar(255) not null comment '密码',
    `username` varchar(64) not null comment '用户名',
    `create_time` datetime not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    `deleted` tinyint(2) not null default 0 comment '删除状态',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
