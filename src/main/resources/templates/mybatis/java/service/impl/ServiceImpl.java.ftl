package ${packageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.mapper.${table.nameUpperCamelCase}Mapper;
import ${packageName}.service.${table.nameUpperCamelCase}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import ${packageName}.domain.dto.*;
import ${packageName}.domain.entity.*;
import ${packageName}.domain.vo.*;
import org.springframework.util.Assert;
import org.springframework.beans.BeanUtils;
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
@Slf4j
@Service
@RequiredArgsConstructor
public class ${table.nameUpperCamelCase}ServiceImpl
    extends ServiceImpl<${table.nameUpperCamelCase}Mapper, ${table.nameUpperCamelCase}>
    implements ${table.nameUpperCamelCase}Service {

    private final ${table.nameUpperCamelCase}Mapper ${table.nameLowerCamelCase}Mapper;
	
    /**
     * 新增
     * @param req
     * @return
     */
    @Override
    public ${table.nameUpperCamelCase} add(${table.nameUpperCamelCase}InsertReq req) {
		${table.nameUpperCamelCase} ${table.nameLowerCamelCase} = new ${table.nameUpperCamelCase}();
		BeanUtils.copyProperties(req, ${table.nameLowerCamelCase});
		${table.nameLowerCamelCase}.preInsert();
		${table.nameLowerCamelCase}Mapper.insert(${table.nameLowerCamelCase});
        return ${table.nameLowerCamelCase};
    }

    /**
     * 修改
     * @param req
     * @return
     */
    @Override
    public ${table.nameUpperCamelCase} modify(${table.nameUpperCamelCase}UpdateReq req) {
	    ${table.nameUpperCamelCase} oldResult = ${table.nameLowerCamelCase}Mapper.selectById(req.getId());
		Assert.notNull(oldResult,"当前操作的数据不存在");
		
        ${table.nameUpperCamelCase} ${table.nameLowerCamelCase} = new ${table.nameUpperCamelCase}();
        BeanUtils.copyProperties(req, ${table.nameLowerCamelCase});
		${table.nameLowerCamelCase}.preUpdate();
        ${table.nameLowerCamelCase}Mapper.updateById(${table.nameLowerCamelCase});
        return ${table.nameLowerCamelCase};
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ${table.nameUpperCamelCase} deleteById(String id) {
	    ${table.nameUpperCamelCase} oldResult = ${table.nameLowerCamelCase}Mapper.selectById(id);
		Assert.notNull(oldResult,"当前操作的数据不存在");
		
        ${table.nameLowerCamelCase}Mapper.deleteById(id);
        return oldResult;
    }
	
    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public ${table.nameUpperCamelCase}Rsp getById(String id) {
	    ${table.nameUpperCamelCase} oldResult = ${table.nameLowerCamelCase}Mapper.selectById(id);
		Assert.notNull(oldResult,"当前操作的数据不存在");
		
        ${table.nameUpperCamelCase}Rsp rsp = new ${table.nameUpperCamelCase}Rsp();
        BeanUtils.copyProperties(oldResult, rsp);
        return rsp;
    }
	

    /**
     * 分页查询
     * @param pageable
     * @param req
     * @return
     */
    @Override
    public IPage<${table.nameUpperCamelCase}ListRsp> listPage(Pageable pageable, ${table.nameUpperCamelCase}ListReq req) {
        Page<${table.nameUpperCamelCase}ListRsp> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
        List<${table.nameUpperCamelCase}ListRsp> list = ${table.nameLowerCamelCase}Mapper.listPage(page, req);
        page.setRecords(list);
        return page;
    }
}