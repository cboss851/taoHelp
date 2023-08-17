package ${packageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${packageName}.domain.entity.*;
import ${packageName}.domain.dto.*;
import ${packageName}.domain.vo.*;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
public interface ${mapperClassName} extends BaseMapper<${entityClassName}> {
	//分页查询
    List<${table.nameUpperCamelCase}ListRsp> listPage(Page<${table.nameUpperCamelCase}ListRsp> page, @Param("query") ${table.nameUpperCamelCase}ListReq req);
}