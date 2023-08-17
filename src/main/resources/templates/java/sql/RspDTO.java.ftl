package ${packageName}.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 *
 *
 * @author ${author}
 **/
@Data
public class ${className} implements Serializable {
<#list fields as field>

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
</#list>

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        <#list fields as field>
        sb.append(", ${field.nameLowerCamelCase}=").append(${field.nameLowerCamelCase});
        </#list>
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}