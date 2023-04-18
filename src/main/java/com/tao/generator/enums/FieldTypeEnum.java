package com.tao.generator.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

/**
 * sql类型与java类型映射
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FieldTypeEnum {
    CHAR(Types.CHAR, String.class, "String","CHAR"),
    VARCHAR(Types.VARCHAR, String.class, "String","VARCHAR"),
    LONGVARCHAR(Types.LONGVARCHAR, String.class, "String","VARCHAR"),
    NUMERIC(Types.NUMERIC, BigDecimal.class, "BigDecimal","NUMERIC"),
    DECIMAL(Types.DECIMAL, BigDecimal.class, "BigDecimal","DECIMAL"),
    BIT(Types.BIT, Boolean.class, "Boolean","BIT"),
    TINYINT(Types.TINYINT, Byte.class, "Byte","TINYINT"),
    SMALLINT(Types.SMALLINT, Short.class, "Short","SMALLINT"),
    INTEGER(Types.INTEGER, Integer.class, "Integer","INTEGER"),
    BIGINT(Types.BIGINT, Long.class, "Long","BIGINT"),
    REAL(Types.REAL, Long.class, "Float","REAL"),
    DOUBLE(Types.DOUBLE, Double.class, "Double","DOUBLE"),
    DATE(Types.DATE, Date.class, "Date","DATE"),
    TIME(Types.TIME, Date.class, "Date","TIMESTAMP"),
    TIMESTAMP(Types.TIMESTAMP, Date.class, "Date","TIMESTAMP"),
    LONGVARBINARY(Types.LONGVARBINARY, String.class, "String","LONGVARBINARY"),
    CLOB(Types.CLOB, String.class, "String","LONGVARCHAR"),
    TEXT(Types.LONGVARCHAR, String.class, "String","LONGVARCHAR"),
    MEDIUMTEXT(Types.LONGVARCHAR, String.class, "String","LONGVARCHAR"),
    LONGTEXT(Types.LONGVARCHAR, String.class, "String","LONGVARCHAR");

    private int sqlType;
    private Class classType;
    private String className;
    //MapperXml中jdbcType类型名称
    private String MapperXmlName;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FieldTypeEnum getEnumBySqlType(int sqlType) {
        for (FieldTypeEnum _enum : values()) {
            if (_enum.getSqlType() == sqlType) {
                return _enum;
            }
        }
        return null;
    }
}