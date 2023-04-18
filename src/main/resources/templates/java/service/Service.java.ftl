package ${packageName}.service.${table.nameLower};

import com.tao.commons.result.ResponseResultPage;
import ${packageName}.service.${table.nameLower}.dto.*;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 * @Date ${date}
 **/
public interface ${table.nameUpperCamelCase}Service {
    void insert(${table.nameUpperCamelCase}InsertReq req);

    void update(${table.nameUpperCamelCase}UpdateReq req);

    void delete(Long ${table.primaryKeyNameLowerCamelCase});

    ResponseResultPage<${table.nameUpperCamelCase}ListRsp> list(${table.nameUpperCamelCase}ListReq req);

    ${table.nameUpperCamelCase}Rsp get${table.nameUpperCamelCase}(Long ${table.primaryKeyNameLowerCamelCase});
}