package com.tao.generator.dto;

import com.tao.generator.enums.QueryConditionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件
 * @Author：cboss
 */
@Data
public class QueryCondition {
    @ApiModelProperty(value = "数据库字段名",example = "order_id")
    private String fieldName;

    @ApiModelProperty(value = "条件名称,数据库字段小驼峰",example = "orderId")
    private String name;

    @ApiModelProperty(value = "条件值",example = "=")
    private String value;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty("java.sql.Types类型名称(列类型名称)")
    private String dataTypeName;

    @ApiModelProperty("java名称")
    private String dataTypeNameJava;

    @ApiModelProperty(value = "条件")
    private String condition;
}