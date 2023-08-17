package ${packageName}.service.${table.nameLower};

import com.tao.commons.result.ResponseResult;
import com.tao.commons.result.ResponseResultPage;
import ${packageName}.service.${table.nameLower}.dto.*;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
public interface ${table.nameUpperCamelCase}Service {
    ResponseResult insert(${table.nameUpperCamelCase}InsertReq req);

    ResponseResult update(${table.nameUpperCamelCase}UpdateReq req);

    ResponseResult delete(Long ${table.primaryKeyNameLowerCamelCase});

    ResponseResultPage<${table.nameUpperCamelCase}ListRsp> list(${table.nameUpperCamelCase}ListReq req);

    ${table.nameUpperCamelCase}Rsp get${table.nameUpperCamelCase}(Long ${table.primaryKeyNameLowerCamelCase});
}