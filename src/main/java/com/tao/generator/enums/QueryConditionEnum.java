package com.tao.generator.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 查询条件
 *
 * @Author：cboss
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QueryConditionEnum {
    EQUAL("equal", "等于"),
    GREATER_THAN("gt", "大于"),
    LESS_THAN("lt", "小于"),
    GREATER_THAN_EQUAL("gte", "大于等于"),
    LESS_THAN_EQUAL("lte", "小于等于"),
    BETWEEN("between", "介于"),//age>20 and age<30
    BETWEEN_EQUAL("between_equal", "介于"),//age>=20 and age<=30
    INCLUDE("include", "包含"),//age in (1,2,3,4,5,6,7,8,9,10)
    LIKE("like", "包含");//name like '%王成%'

    private String code;
    private String name;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static QueryConditionEnum getEnumByCode(String code) {
        for (QueryConditionEnum _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return null;
    }
}