package com.tao.generator.mybatis.template;

import com.tao.datat.service.database.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 根据SQL生成代码
 *
 * @Author：cboss
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorGetDto {
    @ApiModelProperty(value = "SQL语句")
    private String sql;

    @ApiModelProperty(value = "控制类名",example = "com.tao.space.controller.ExampleNiceController")
    private String controllerClassName;

    @ApiModelProperty(value = "服务类名",example = "com.tao.space.service.examplenice.ExampleNiceService")
    private String serviceClassName;

    @ApiModelProperty(value = "mapper类名",example = "com.tao.space.mapper.custom.CustomExampleNiceMapper")
    private String mapperClassName;

    @ApiModelProperty(value = "方法名",example = "getExampleByOrderNo")
    private String methodName;

    @ApiModelProperty(value = "参数名称",example = "orderNo")
    private String parameterName;

    @ApiModelProperty(value = "参数名称class",example = "String/Long")
    private String parameterNameClass;

    @ApiModelProperty(value = "DTO名称",example = "ExampleNiceOrder")
    private String dtoName;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("模块描述")
    private String description;
    //当前日期
    private String date;
    //数据库表
    private List<TableField> fields;
}