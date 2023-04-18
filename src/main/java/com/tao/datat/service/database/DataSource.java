package com.tao.datat.service.database;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 数据源
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSource implements Serializable {
    @ApiModelProperty(value = "",example = "com.mysql.cj.jdbc.Driver",required = true)
    private String driverClassName;

    @ApiModelProperty(value = "URL",example = "jdbc:mysql://localhost:3306/mybatis_example?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false&serverTimezone=Asia/Shanghai&&allowPublicKeyRetrieval=true",required = true)
    private String url;

    @ApiModelProperty(value = "登录名",example = "root",required = true)
    private String username;

    @ApiModelProperty(value = "密码",example = "ea8dc0d1fe7e4073ad0296ea2eacc651",required = true)
    private String password;

    private static final long serialVersionUID = 1L;
}