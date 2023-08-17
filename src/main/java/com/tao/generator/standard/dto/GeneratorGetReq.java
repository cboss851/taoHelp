package com.tao.generator.standard.dto;

import com.tao.datat.service.database.DataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：cboss
 */
@Data
public class GeneratorGetReq {
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

    @ApiModelProperty("数据源")
    DataSource ds;

    @ApiModelProperty(value = "输出目录",example = "D:\\workspace\\my\\taoHelp\\src\\main")
    private String outputPath;
}