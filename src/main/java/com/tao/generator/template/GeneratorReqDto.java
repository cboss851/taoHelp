package com.tao.generator.template;

import com.tao.datat.service.database.TableField;
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
public class GeneratorReqDto {
    //全局包路径
    private String packageName;
    //类名
    private String className;
    //作者
    private String author;
    //当前日期
    private String date;
    //数据库表
    private List<TableField> fields;
}