package com.tao.space.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.UserService;
import com.tao.space.service.user.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @Author：cboss
 * @Date：2023/3/25
 */
@Api(tags = "用户")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "新增", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->新增")
    @PostMapping("/insert")
    public ResponseResult insert(@Validated @RequestBody UserInsertReq req) {
        userService.insert(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "修改", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情->修改")
    @PostMapping("/update")
    public ResponseResult update(@Validated @RequestBody UserUpdateReq req) {
        userService.update(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "修改-分布锁", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情->修改")
    @PostMapping("/updateDistributedLock")
    public ResponseResult updateDistributedLock(@Validated @RequestBody UserUpdateReq req) {
        userService.updateDistributedLock(req);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情->删除")
    @GetMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseResult.success();
    }

    @ApiOperation(value = "分页列表", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->分页列表")
    @PostMapping("/list")
    public ResponseResultPage<UserListRsp> list(@Validated @RequestBody UserListReq req) {
        return userService.list(req);
    }

    @ApiOperation(value = "详情", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情")
    @GetMapping("/get/{id}")
    public ResponseResult<UserRsp> getUser(@PathVariable Long id) {
        return ResponseResult.successData(userService.getUser(id));
    }

    @ApiOperation(value = "详情cache", notes = "作者：cboss <br/> 模块功能：v0.0.1->用户管理->用户详情")
    @GetMapping("/get/cache/{id}")
    public ResponseResult<UserRsp> getUserCache(@PathVariable Long id) {
        return ResponseResult.successData(userService.getUserCache(id));
    }

}