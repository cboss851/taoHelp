package ${packageName}.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bifrost.modules.system.entity.AbstractEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
*
* ${table.comment!}
*
* @author ${author}
**/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${table.nameUpperCamelCase}InsertReq implements Serializable{
<#list table.fields as field>
<#if field.isPrimaryKey != 1 && field.name != "created_by" && field.name != "created_time"
    && field.name != "updated_by" && field.name != "updated_time" && field.name != "archived">

    @ApiModelProperty(value = "${field.comment}"<#if field.isNull == "NO">,required = true</#if>)
    <#if field.dataTypeNameJava == "String">
    @Size(max = ${field.length?c},message = "${field.nameLowerCamelCase}超过最大长度限制,最大长度为：${field.length?c}")
    </#if>
    <#if field.isNull == "NO">
    @NotBlank(message = "${field.nameLowerCamelCase}不能为空")
    </#if>
    <#if field.dataTypeName == "DATE">
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    </#if>
    <#if field.dataTypeName == "DATETIME">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    </#if>
    private ${field.dataTypeNameJava} ${field.nameLowerCamelCase};
</#if>
</#list>
}