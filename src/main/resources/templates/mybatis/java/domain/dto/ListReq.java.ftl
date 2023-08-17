package ${packageName}.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
*
* ${table.comment!}
*
* @author ${author}
**/
@Data
public class ${table.nameUpperCamelCase}ListReq {
    @ApiModelProperty(value = "页码", example = "1", required = true)
    private Integer page = 1;

    @ApiModelProperty(value = "分页长度", example = "20", required = true)
    private Integer size = 10;
	
}
