package ${packageName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${packageName}.domain.dto.*;
import ${packageName}.domain.entity.*;
import ${packageName}.domain.vo.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
public interface ${table.nameUpperCamelCase}Service extends IService<${table.nameUpperCamelCase}>{
    //新增
    ${table.nameUpperCamelCase} add(${table.nameUpperCamelCase}InsertReq req);

    //修改
    ${table.nameUpperCamelCase} modify(${table.nameUpperCamelCase}UpdateReq req);

    //删除
    ${table.nameUpperCamelCase} deleteById(String id);

    //查询
    ${table.nameUpperCamelCase}Rsp getById(String id);

    //分页查询
    IPage<${table.nameUpperCamelCase}ListRsp> listPage(Pageable pageable, ${table.nameUpperCamelCase}ListReq req);
}