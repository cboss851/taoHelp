package com.tao.generator.mybatis.template;

import com.tao.datat.service.database.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生成代码数据模型
 *
 * @Author：cboss
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorModel {
    //全局包路径
    private String packageName;
    //service包路径：packageName+service
    private String servicePackageName;
    //作者
    private String author;
    //当前日期
    private String date;
    //数据库表
    private Table table;
    //表实体类名
    private String entityClassName;
    //表mapper名
    private String mapperClassName;
    //定制表mapper名
    private String customMapperClassName;
    //表mapper模板文件名
    private String mapperXmlName;
    //定制表mapper模板文件名
    private String customMapperXmlName;
    //service模板文件名
    private String serviceClassName;
    //控制层模板文件名
    private String controllerClassName;

}
