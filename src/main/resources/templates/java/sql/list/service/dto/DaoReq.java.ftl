package ${servicePackageName}.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Date;

/**
 *
 *
 * @author ${author}
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${dtoName}DaoReq {
<#list conditionListDto as condition>

    @ApiModelProperty(value = "${condition.comment}")
    <#if condition.dataTypeName == "DATE">
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    </#if>
    <#if condition.dataTypeName == "DATETIME" || condition.dataTypeName == "TIMESTAMP">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    </#if>
    private ${condition.dataTypeNameJava} ${condition.name};
</#list>
}