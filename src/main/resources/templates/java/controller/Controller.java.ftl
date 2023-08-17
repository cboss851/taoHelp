package ${packageName}.controller;

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import ${packageName}.service.${table.nameLower}.${table.nameUpperCamelCase}Service;
import ${packageName}.service.${table.nameLower}.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
@Api(tags = "${table.nameLower}")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/${table.nameLowerCamelCase}")
public class ${table.nameUpperCamelCase}Controller {
    private final ${table.nameUpperCamelCase}Service ${table.nameLowerCamelCase}Service;

    @ApiOperation(value = "新增", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->新增")
    @PostMapping("/insert")
    public ResponseResult insert(@Validated @RequestBody ${table.nameUpperCamelCase}InsertReq req) {
        return ${table.nameLowerCamelCase}Service.insert(req);
    }

    @ApiOperation(value = "修改", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->详情->修改")
    @PostMapping("/update")
    public ResponseResult update(@Validated @RequestBody ${table.nameUpperCamelCase}UpdateReq req) {
        return ${table.nameLowerCamelCase}Service.update(req);
    }

    @ApiOperation(value = "删除", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->详情->删除")
    @GetMapping("/delete/{${table.primaryKeyNameLowerCamelCase}}")
    public ResponseResult delete(@PathVariable Long ${table.primaryKeyNameLowerCamelCase}) {
        return ${table.nameLowerCamelCase}Service.delete(${table.primaryKeyNameLowerCamelCase});
    }

    @ApiOperation(value = "分页列表", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->分页列表")
    @PostMapping("/list")
    public ResponseResultPage<${table.nameUpperCamelCase}ListRsp> list(@Validated @RequestBody ${table.nameUpperCamelCase}ListReq req) {
        return ${table.nameLowerCamelCase}Service.list(req);
    }

    @ApiOperation(value = "详情", notes = "作者：${author} <br/> 模块功能：v0.0.1->管理->详情")
    @GetMapping("/get/{${table.primaryKeyNameLowerCamelCase}}")
    public ResponseResult<${table.nameUpperCamelCase}Rsp> get${table.nameUpperCamelCase}(@PathVariable Long ${table.primaryKeyNameLowerCamelCase}) {
        return ResponseResult.successData(${table.nameLowerCamelCase}Service.get${table.nameUpperCamelCase}(${table.primaryKeyNameLowerCamelCase}));
    }
}