package com.tao.generator.mybatis.dto;

import com.tao.datat.service.database.DataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author：cboss
 * @Date：2023/4/8
 */
@Data
public class GeneratorListReq {
    @ApiModelProperty(value = "SQL语句")
    private String sql;

    @ApiModelProperty(value = "控制类名", example = "com.tao.space.controller.ExampleNiceController")
    private String controllerClassName;

    @ApiModelProperty(value = "服务类名", example = "com.tao.space.service.examplenice.ExampleNiceService")
    private String serviceClassName;

    @ApiModelProperty(value = "mapper类名", example = "com.tao.space.mapper.custom.CustomExampleNiceMapper")
    private String mapperClassName;

    @ApiModelProperty(value = "方法名", example = "listOrder")
    private String methodName;

    @ApiModelProperty(value = "DTO名称", example = "ExampleNiceOrderList")
    private String dtoName;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("模块描述")
    private String description;

    @ApiModelProperty("数据源")
    DataSource ds;

    @ApiModelProperty(value = "输出目录", example = "D:\\workspace\\my\\taoHelp\\src\\main")
    private String outputPath;

    @ApiModelProperty(value = "查询条件")
    private List<QueryCondition> conditionList;
}
