package com.tao.generator.dto;

import com.tao.datat.service.database.DataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：cboss
 * @Date：2023/4/14
 */
@Data
public class GeneratorQueryConditionReq {
    @ApiModelProperty("数据源")
    DataSource ds;

    @ApiModelProperty(value = "SQL语句")
    private String sql;
}
