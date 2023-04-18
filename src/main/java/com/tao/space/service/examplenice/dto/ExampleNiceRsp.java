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
public class ExampleNiceRsp implements Serializable {

    @ApiModelProperty(value = "ID",required = true)
    @NotNull(message = "exampleId不能为空")
    private Long exampleId;

    @ApiModelProperty(value = "交易号")
    private Long tradeId;

    @ApiModelProperty(value = "定单号")
    @Size(max = 64,message = "orderNo超过最大长度限制,最大长度为：64")
    private String orderNo;

    @ApiModelProperty(value = "名称",required = true)
    @Size(max = 100,message = "name超过最大长度限制,最大长度为：100")
    @NotBlank(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "")
    @Size(max = 255,message = "type超过最大长度限制,最大长度为：255")
    private String type;

    @ApiModelProperty(value = "char_test测试")
    @Size(max = 30,message = "charTest超过最大长度限制,最大长度为：30")
    private String charTest;

    @ApiModelProperty(value = "")
    @Size(max = 65535,message = "blobTest超过最大长度限制,最大长度为：65535")
    private String blobTest;

    @ApiModelProperty(value = "")
    @Size(max = 65535,message = "textTest超过最大长度限制,最大长度为：65535")
    private String textTest;

    @ApiModelProperty(value = "")
    @Size(max = 16777215,message = "mediumtextTest超过最大长度限制,最大长度为：16777215")
    private String mediumtextTest;

    @ApiModelProperty(value = "")
    @Size(max = 2147483647,message = "longtextTest超过最大长度限制,最大长度为：2147483647")
    private String longtextTest;

    @ApiModelProperty(value = "")
    private Byte tinyintAbc;

    @ApiModelProperty(value = "")
    private Short smallintAbc;

    @ApiModelProperty(value = "")
    private Integer intAbc;

    @ApiModelProperty(value = "")
    private Long bigintAbc;

    @ApiModelProperty(value = "")
    private Float floatAbc;

    @ApiModelProperty(value = "")
    private Double doubleAbc;

    @ApiModelProperty(value = "")
    private BigDecimal decimalAbc;

    @ApiModelProperty(value = "")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date dateAbc;

    @ApiModelProperty(value = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date datetimeAbc;

    @ApiModelProperty(value = "记录创建时间")
    private Date createTime;

    @ApiModelProperty(value = "记录更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}