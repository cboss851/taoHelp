package ${packageName}.service.${table.nameLower}.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import com.tao.commons.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import ${packageName}.mapper.${table.nameUpperCamelCase}Mapper;
import ${packageName}.mapper.custom.Custom${table.nameUpperCamelCase}Mapper;
import ${packageName}.entity.${table.nameUpperCamelCase};
import ${packageName}.service.${table.nameLower}.${table.nameUpperCamelCase}Service;
import ${packageName}.service.${table.nameLower}.dto.*;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ${table.nameUpperCamelCase}ServiceImpl implements ${table.nameUpperCamelCase}Service {
    private final ${table.nameUpperCamelCase}Mapper ${table.nameLowerCamelCase}Mapper;
    private final Custom${table.nameUpperCamelCase}Mapper custom${table.nameUpperCamelCase}Mapper;

    @Override
    public ResponseResult insert(${table.nameUpperCamelCase}InsertReq req){
        ${table.nameUpperCamelCase} ${table.nameLowerCamelCase} = new ${table.nameUpperCamelCase}();
        BeanUtils.copyProperties(req, ${table.nameLowerCamelCase});
        ${table.nameLowerCamelCase}Mapper.insertSelective(${table.nameLowerCamelCase});
		return ResponseResult.success();
    }

    @Override
    public ResponseResult update(${table.nameUpperCamelCase}UpdateReq req){
        ${table.nameUpperCamelCase} ${table.nameLowerCamelCase} = new ${table.nameUpperCamelCase}();
        BeanUtils.copyProperties(req, ${table.nameLowerCamelCase});
        ${table.nameLowerCamelCase}Mapper.updateByPrimaryKeySelective(${table.nameLowerCamelCase});
		return ResponseResult.success();
    }

    @Override
    public ResponseResult delete(Long ${table.primaryKeyNameLowerCamelCase}){
        ${table.nameLowerCamelCase}Mapper.deleteByPrimaryKey(${table.primaryKeyNameLowerCamelCase});
		return ResponseResult.success();
    }

    @Override
    public ResponseResultPage<${table.nameUpperCamelCase}ListRsp> list(${table.nameUpperCamelCase}ListReq req){
        Page<${table.nameUpperCamelCase}ListRsp> page = PageHelper.startPage(req.getPage(), req.getSize());

        ${table.nameUpperCamelCase}ListDaoReq daoReq = BeanUtils.copyBean(req, ${table.nameUpperCamelCase}ListDaoReq.class);
        List<${table.nameUpperCamelCase}ListModel> modelList = custom${table.nameUpperCamelCase}Mapper.list(daoReq);

        List<${table.nameUpperCamelCase}ListRsp> rspList = BeanUtils.copyBeanList(modelList, ${table.nameUpperCamelCase}ListRsp.class);
        return ResponseResultPage.page(page.getTotal(), rspList);
    }

    @Override
    public ${table.nameUpperCamelCase}Rsp get${table.nameUpperCamelCase}(Long ${table.primaryKeyNameLowerCamelCase}){
        ${table.nameUpperCamelCase} ${table.nameLowerCamelCase} = ${table.nameLowerCamelCase}Mapper.selectByPrimaryKey(${table.primaryKeyNameLowerCamelCase});
        return BeanUtils.copyBean(${table.nameLowerCamelCase}, ${table.nameUpperCamelCase}Rsp.class);
    }
}