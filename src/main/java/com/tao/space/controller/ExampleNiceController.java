package com.tao.space.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.examplenice.ExampleNiceService;
import com.tao.space.service.examplenice.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
@Api(tags = "examplenice")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exampleNice")
public class ExampleNiceController {
    private final ExampleNiceService exampleNiceService;

    @ApiOperation(value = "新增", notes = "作者：cboss <br/> 模块功能：v0.0.1->管理->新增")
    @PostMapping("/insert")
    public ResponseResult insert(@Validated @RequestBody ExampleNiceInsertReq req) {
        exampleNiceService.insert(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "修改", notes = "作者：cboss <br/> 模块功能：v0.0.1->管理->详情->修改")
    @PostMapping("/update")
    public ResponseResult update(@Validated @RequestBody ExampleNiceUpdateReq req) {
        exampleNiceService.update(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除", notes = "作者：cboss <br/> 模块功能：v0.0.1->管理->详情->删除")
    @GetMapping("/delete/{exampleId}")
    public ResponseResult delete(@PathVariable Long exampleId) {
        exampleNiceService.delete(exampleId);
        return ResponseResult.success();
    }

    @ApiOperation(value = "分页列表", notes = "作者：cboss <br/> 模块功能：v0.0.1->管理->分页列表")
    @PostMapping("/list")
    public ResponseResultPage<ExampleNiceListRsp> list(@Validated @RequestBody ExampleNiceListReq req) {
        return exampleNiceService.list(req);
    }

    @ApiOperation(value = "详情", notes = "作者：cboss <br/> 模块功能：v0.0.1->管理->详情")
    @GetMapping("/get/{exampleId}")
    public ResponseResult<ExampleNiceRsp> getExampleNice(@PathVariable Long exampleId) {
        return ResponseResult.successData(exampleNiceService.getExampleNice(exampleId));
    }

    @ApiOperation(value = "订单列表", notes = "作者： <br/> 模块功能：v0.0.1->管理->详情")
    @PostMapping("/listOrder")
    public ResponseResult<ExampleNiceOrderListRsp> listOrder(@Validated @RequestBody ExampleNiceOrderListReq req) {
        return ResponseResult.successData(exampleNiceService.listOrder(req));
    }
}