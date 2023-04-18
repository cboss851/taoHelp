package com.tao.space.service.examplenice.dto;

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
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleNiceListDaoReq {
    @ApiModelProperty(value = "关键字")
    private String keyWords;
}