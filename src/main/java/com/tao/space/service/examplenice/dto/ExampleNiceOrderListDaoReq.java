package com.tao.space.service.examplenice.dto;

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
 * @author 
 * @Date 2023-04-15
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExampleNiceOrderListDaoReq {

    @ApiModelProperty(value = "ID")
    private Long exampleId;

    @ApiModelProperty(value = "交易号")
    private Long tradeId;

    @ApiModelProperty(value = "定单号")
    private String orderNo;

    @ApiModelProperty(value = "")
    private Integer intAbc;

    @ApiModelProperty(value = "")
    private Short smallintAbc;

    @ApiModelProperty(value = "")
    private Long bigintAbcStart;

    @ApiModelProperty(value = "")
    private Long bigintAbcEnd;

    @ApiModelProperty(value = "记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTimeStart;

    @ApiModelProperty(value = "记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTimeEnd;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "")
    private List<String> typeList;
}