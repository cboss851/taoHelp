package com.tao.generator.standard.dto;

import com.tao.datat.service.database.DataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：cboss
 */
@Data
public class GeneratorReq {
    @ApiModelProperty("包名")
    private String packageName;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("数据源")
    DataSource ds;

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表主键")
    private String primaryKeyName;

    @ApiModelProperty("模式")
    private String schema;

    @ApiModelProperty(value = "输出目录", example = "D:\\workspace\\my\\taoHelp\\src\\main")
    private String outputPath;
}