package com.tao.generator.standard;

import com.tao.generator.standard.dto.*;

import java.util.List;

/**
 * @Author：cboss
 */
public interface GeneratorService {
    void generatorCodeAll(GeneratorReq generatorReq) throws Exception;

    String generatorReqDTO(GeneratorReqDTO req) throws Exception;

    String generatorCodeGet(GeneratorGetReq req) throws Exception;

    String generatorCodeList(GeneratorListReq req) throws Exception;

    List<QueryCondition> generatorQueryCondition(GeneratorQueryConditionReq req) throws Exception;

    void generatorCodeMapperAndEntityDTO(GeneratorReq generatorReq) throws Exception;
}
