package ${servicePackageName}.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tao.commons.result.RequestPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author ${author}
 * @Date ${date}
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${dtoName}Req extends RequestPage implements Serializable {
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

    private static final long serialVersionUID = 1L;
}