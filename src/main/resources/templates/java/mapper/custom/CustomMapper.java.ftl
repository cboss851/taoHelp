package ${packageName}.mapper.custom;

import ${packageName}.service.${table.nameLower}.dto.${table.nameUpperCamelCase}ListModel;
import ${packageName}.service.${table.nameLower}.dto.${table.nameUpperCamelCase}ListDaoReq;

import java.util.List;

/**
* ${table.comment!}
*
* @author ${author}
*/
public interface Custom${table.nameUpperCamelCase}Mapper {
    List<${table.nameUpperCamelCase}ListModel> list(${table.nameUpperCamelCase}ListDaoReq req);
}