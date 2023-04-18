package com.tao.datat.service.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableField implements Serializable {
    private String name;//列名
    private String nameLowerCamelCase;//列名-驼峰
    private int dataType;//对应的java.sql.Types的SQL类型(列类型ID)
    private String dataTypeName;//java.sql.Types类型名称(列类型名称)
    private String dataTypeNameJava;//java名称
    private String dataTypeNameMapperXml;//mapperXml类型名称
    private int isPrimaryKey;//1是主键，其它不是
    private int columnSize;//列大小
    private String comment;
    private int length;//长度
    private String isNull;//是否允许空
    private String defaultValue;//默认值

    private static final long serialVersionUID = 1L;
}
