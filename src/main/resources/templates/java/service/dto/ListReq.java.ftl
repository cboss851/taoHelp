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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${table.nameUpperCamelCase}ListReq extends RequestPage implements Serializable {
    @ApiModelProperty(value = "关键字")
    private String keyWords;

    private static final long serialVersionUID = 1L;
}