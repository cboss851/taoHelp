package com.tao.generator.mybatis;

import com.tao.generator.mybatis.dto.*;

import java.util.List;

/**
 * @Author：cboss
 */
public interface GeneratorMybatisService {
    void generatorCodeAll(GeneratorReq generatorReq) throws Exception;

    String generatorReqDTO(GeneratorReqDTO req) throws Exception;

    String generatorCodeGet(GeneratorGetReq req) throws Exception;

    String generatorCodeList(GeneratorListReq req) throws Exception;

    List<QueryCondition> generatorQueryCondition(GeneratorQueryConditionReq req) throws Exception;

    void generatorCodeMapperAndEntityDTO(GeneratorReq generatorReq) throws Exception;
}
