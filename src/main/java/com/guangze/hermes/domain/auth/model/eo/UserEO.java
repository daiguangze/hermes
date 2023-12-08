package com.guangze.hermes.domain.auth.model.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEO {

    private Long id;

    private String account;

    private String password;

    private String username;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

}
