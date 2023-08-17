package com.tao.space.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.generator.standard.GeneratorService;
import com.tao.generator.standard.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：cboss
 */
@Api(tags = "生成代码-标准")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
public class GeneratorController {
    private final GeneratorService generatorService;

    @ApiOperation(value = "生成代码全部")
    @PostMapping("/generatorCodeAll")
    public ResponseResult generatorCodeAll(@Validated @RequestBody GeneratorReq generatorReq) throws Exception {
        generatorService.generatorCodeAll(generatorReq);
        return ResponseResult.success();
    }

    @ApiOperation(value = "生成代码MapperAndEntityDTO")
    @PostMapping("/generatorCodeMapperAndEntityDTO")
    public ResponseResult generatorCodeMapperAndEntityDTO(@Validated @RequestBody GeneratorReq generatorReq) throws Exception {
        generatorService.generatorCodeMapperAndEntityDTO(generatorReq);
        return ResponseResult.success();
    }

    @ApiOperation(value = "生成ReqDTO")
    @PostMapping("/generatorReqDTO")
    public String generatorReqDTO(@Validated @RequestBody GeneratorReqDTO req) throws Exception {
        return generatorService.generatorReqDTO(req);
    }

    @ApiOperation(value = "生成单对象查询")
    @PostMapping("/generatorCodeGet")
    public ResponseResult generatorCodeGet(@Validated @RequestBody GeneratorGetReq req) throws Exception {
        generatorService.generatorCodeGet(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "生成分页列表查询")
    @PostMapping("/generatorCodeList")
    public ResponseResult generatorCodeList(@Validated @RequestBody GeneratorListReq req) throws Exception {
        generatorService.generatorCodeList(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "解析SQL语句输出查询条件")
    @PostMapping("/generatorQueryCondition")
    public ResponseResult<List<QueryCondition>> generatorQueryCondition(@Validated @RequestBody GeneratorQueryConditionReq req) throws Exception {
        return ResponseResult.successData(generatorService.generatorQueryCondition(req));
    }
}