package ${packageName}.mapper;

import ${packageName}.entity.${entityClassName};

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
public interface ${mapperClassName} {
    int deleteByPrimaryKey(Long ${table.primaryKeyNameLowerCamelCase});

    int insert(${table.nameUpperCamelCase} record);

    int insertSelective(${table.nameUpperCamelCase} record);

    ${table.nameUpperCamelCase} selectByPrimaryKey(Long ${table.primaryKeyNameLowerCamelCase});

    int updateByPrimaryKeySelective(${table.nameUpperCamelCase} record);

    int updateByPrimaryKey(${table.nameUpperCamelCase} record);
}