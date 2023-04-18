package com.tao.space.service.examplenice.dto;

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
 * @author 
 * @Date 2023-04-15
 **/
@Data
public class ExampleNiceOrderListRsp implements Serializable {

    @ApiModelProperty(value = "ID",required = true)
    @NotBlank(message = "exampleId不能为空")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", exampleId=").append(exampleId);
        sb.append(", tradeId=").append(tradeId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", charTest=").append(charTest);
        sb.append(", blobTest=").append(blobTest);
        sb.append(", textTest=").append(textTest);
        sb.append(", mediumtextTest=").append(mediumtextTest);
        sb.append(", longtextTest=").append(longtextTest);
        sb.append(", tinyintAbc=").append(tinyintAbc);
        sb.append(", smallintAbc=").append(smallintAbc);
        sb.append(", intAbc=").append(intAbc);
        sb.append(", bigintAbc=").append(bigintAbc);
        sb.append(", floatAbc=").append(floatAbc);
        sb.append(", doubleAbc=").append(doubleAbc);
        sb.append(", decimalAbc=").append(decimalAbc);
        sb.append(", dateAbc=").append(dateAbc);
        sb.append(", datetimeAbc=").append(datetimeAbc);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}