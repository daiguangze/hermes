package com.guangze.hermes.infrastructure.auth.persistence;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user")
public class UserDO {

    @TableField("id")
    private Long id;

    @TableField("account")
    private String account;


    @TableField("password")
    private String password;

    @TableField("username")
    private String username;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("deleted")
    private Integer deleted;

}
