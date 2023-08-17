package com.tao.generator.mybatis.dto;

import com.tao.datat.service.database.DataSource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：cboss
 * @Date：2023/4/8
 */
@Data
public class GeneratorReqDTO {
    @ApiModelProperty(value = "SQL语句")
    private String sql;

    @ApiModelProperty("包名")
    private String packageName;

    @ApiModelProperty("类名")
    private String className;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("数据源")
    DataSource ds;

    @ApiModelProperty(value = "输出目录",example = "D:\\workspace\\my\\taoHelp\\src\\main")
    private String outputPath;
}