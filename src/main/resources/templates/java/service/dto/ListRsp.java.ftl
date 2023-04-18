package ${packageName}.service.${table.nameLower}.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tao.commons.result.RequestPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 * @Date ${date}
 **/
@Data
public class ${table.nameUpperCamelCase}ListRsp implements Serializable {
<#list table.fields as field>

    @ApiModelProperty(value = "${field.comment}"<#if field.isNull == "NO">,required = true</#if>)
    <#if field.dataTypeNameJava == "String">
    @Size(max = ${field.length?c},message = "${field.nameLowerCamelCase}超过最大长度限制,最大长度为：${field.length?c}")
    </#if>
    <#if field.isNull == "NO">
    <#if field.dataTypeNameJava == "String">@NotBlank(message = "${field.nameLowerCamelCase}不能为空")</#if><#if field.dataTypeNameJava != "String">@NotNull(message = "${field.nameLowerCamelCase}不能为空")</#if>
    </#if>
    <#if field.dataTypeName == "DATE">
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    </#if>
    <#if field.dataTypeName == "DATETIME">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    </#if>
    private ${field.dataTypeNameJava} ${field.nameLowerCamelCase};
</#list>

    private static final long serialVersionUID = 1L;
}