package ${packageName}.service.${table.nameLower};

import ${packageName}.service.${table.nameLower}.dto.${table.nameUpperCamelCase}CacheRsp;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 * @Date ${date}
 **/
public interface ${table.nameUpperCamelCase}ManagerService {

    ${table.nameUpperCamelCase}CacheRsp get${table.nameUpperCamelCase}Cache(Long ${table.primaryKeyNameLowerCamelCase});
}
