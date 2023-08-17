package ${packageName}.controller;


import ${packageName}.service.${table.nameUpperCamelCase}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ${packageName}.domain.dto.*;
import ${packageName}.domain.entity.*;
import ${packageName}.domain.vo.*;
import com.bifrost.common.api.vo.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
@Api(tags = "${table.comment!}")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/${table.nameLowerCamelCase}")
public class ${table.nameUpperCamelCase}Controller {
    private final ${table.nameUpperCamelCase}Service ${table.nameLowerCamelCase}Service;

    @ApiOperation(value = "新增", notes = "作者：${author} <br/> 模块功能：v0.0.1->${table.comment!}->新增")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody ${table.nameUpperCamelCase}InsertReq req) {
		return Result.ok(${table.nameLowerCamelCase}Service.add(req),"新增成功");
    }

    @ApiOperation(value = "修改", notes = "作者：${author} <br/> 模块功能：v0.0.1->${table.comment!}->修改")
    @PutMapping("/update")
    public Result update(@Validated @RequestBody ${table.nameUpperCamelCase}UpdateReq req) {
		return Result.ok(${table.nameLowerCamelCase}Service.modify(req),"修改成功");
    }

    @ApiOperation(value = "删除", notes = "作者：${author} <br/> 模块功能：v0.0.1->${table.comment!}->删除")
    @DeleteMapping("/delete/{${table.primaryKeyNameLowerCamelCase}}")
    public Result delete(@PathVariable String ${table.primaryKeyNameLowerCamelCase}) {
		return Result.ok(${table.nameLowerCamelCase}Service.deleteById(${table.primaryKeyNameLowerCamelCase}),"删除成功");
    }

    @ApiOperation(value = "详情", notes = "作者：${author} <br/> 模块功能：v0.0.1->${table.comment!}->详情")
    @GetMapping("/get/{${table.primaryKeyNameLowerCamelCase}}")
    public Result get${table.nameUpperCamelCase}(@PathVariable String ${table.primaryKeyNameLowerCamelCase}) {
		return Result.ok(${table.nameLowerCamelCase}Service.getById(${table.primaryKeyNameLowerCamelCase}));
    }
	
    @ApiOperation(value = "分页查询", notes = "作者：${author} <br/> 模块功能：v0.0.1->${table.comment!}->分页查询")
    @GetMapping("/list")
    public Result<?> listPage(@PageableDefault Pageable pageable, ${table.nameUpperCamelCase}ListReq req) {
        return Result.ok(${table.nameLowerCamelCase}Service.listPage(pageable, req));
    }
}