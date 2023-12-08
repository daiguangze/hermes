package com.guangze.hermes.infrastructure.auth.persistence;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserTestDO {
    @TableField("TID")
    private Long Tid;

    @TableField("AA")
    private String aa;

    @TableField("BB")
    private String bb;

    @TableField("CC")
    private String cc;

    @TableField("DD")
    private String dd;

    @TableField("create_time")
    private Date createTime;
}
