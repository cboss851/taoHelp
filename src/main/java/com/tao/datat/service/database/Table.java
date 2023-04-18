package com.tao.datat.service.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Table implements Serializable {
    private Database database;
    private List<TableField> fields;
    private List<TableField> fieldsNotPrimaryKey;
    private String cat;
    private String schema;
    private String name;
    private String nameUpperCamelCase;//表名-首字母大写驼峰
    private String nameLowerCamelCase;//表名-首字母小线驼峰
    private String nameLower;//表名小写，去下划线的
    private String nameUpper;//表名小写，去下划线的
    private String type;
    private String comment;
    private Long count;
    private List<String> primaryKey;

    private String primaryKeyName;//主键字段名称
    private String primaryKeyNameLowerCamelCase;//主键-驼峰

    public TableField getField(String fieldName){
        for (TableField field : fields) {
            if(fieldName.equalsIgnoreCase(field.getName())){
                return field;
            }
        }
        return null;
    }
    private static final long serialVersionUID = 1L;
}
