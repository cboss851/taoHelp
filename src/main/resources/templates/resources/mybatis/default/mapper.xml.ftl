<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${mapperClassName}">
    <resultMap id="BaseResultMap" type="${packageName}.entity.${entityClassName}">
<#list table.fields as field>
        <#if field.isPrimaryKey == 1>
        <id column="${field.name}" jdbcType="${field.dataTypeNameMapperXml}" property="${field.nameLowerCamelCase}" />
        </#if>
        <#if field.isPrimaryKey != 1>
        <result column="${field.name}" jdbcType="${field.dataTypeNameMapperXml}" property="${field.nameLowerCamelCase}" />
        </#if>
</#list>
    </resultMap>
    <sql id="Base_Column_List">
        <#list table.fields as field><#if field_index!=0>,</#if>${field.name}</#list>
    </sql>
    <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${table.name}
        where ${table.primaryKeyName} = ${r"#{"}${table.primaryKeyNameLowerCamelCase},jdbcType=BIGINT${r"}"}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
        delete from ${table.name}
        where ${table.primaryKeyName} = ${r"#{"}${table.primaryKeyNameLowerCamelCase},jdbcType=BIGINT${r"}"}
    </delete>
    <insert id="insert" parameterType="${packageName}.entity.${entityClassName}">
        <selectKey keyProperty="${table.primaryKeyNameLowerCamelCase}" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${table.name} (<#list table.fieldsNotPrimaryKey as field><#if field_index!=0>,</#if>${field.name}</#list>)
        values (<#list table.fieldsNotPrimaryKey as field><#if field_index!=0>,</#if>${r"#{"}${field.nameLowerCamelCase},jdbcType=${field.dataTypeNameMapperXml}${r"}"}</#list>)
    </insert>
    <insert id="insertSelective" parameterType="${packageName}.entity.${entityClassName}">
        <selectKey keyProperty="${table.primaryKeyNameLowerCamelCase}" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        insert into ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list table.fieldsNotPrimaryKey as field>
          <if test="${field.nameLowerCamelCase} != null">
              ${field.name},
          </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list table.fieldsNotPrimaryKey as field>
          <if test="${field.nameLowerCamelCase} != null">
              ${r"#{"}${field.nameLowerCamelCase},jdbcType=${field.dataTypeNameMapperXml}${r"}"},
          </if>
        </#list>
        </trim>
    </insert>
    <update id="updateByPrimaryKeySelective" parameterType="${packageName}.entity.${entityClassName}">
        update ${table.name}
        <set>
        <#list table.fieldsNotPrimaryKey as field>
          <if test="${field.nameLowerCamelCase} != null">
              ${field.name} = ${r"#{"}${field.nameLowerCamelCase},jdbcType=${field.dataTypeNameMapperXml}${r"}"},
          </if>
        </#list>
        </set>
        where ${table.primaryKeyName} = ${r"#{"}${table.primaryKeyNameLowerCamelCase},jdbcType=BIGINT${r"}"}
    </update>
    <update id="updateByPrimaryKey" parameterType="${packageName}.entity.${entityClassName}">
        update ${table.name}
        set
          <#list table.fieldsNotPrimaryKey as field>
          ${field.name} = ${r"#{"}${field.nameLowerCamelCase},jdbcType=${field.dataTypeNameMapperXml}${r"}"}<#if field_has_next>,</#if>
          </#list>
        where ${table.primaryKeyName} = ${r"#{"}${table.primaryKeyNameLowerCamelCase},jdbcType=BIGINT${r"}"}
    </update>
</mapper>