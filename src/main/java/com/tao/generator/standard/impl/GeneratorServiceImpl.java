package com.tao.generator.standard.impl;

import com.tao.commons.utils.*;
import com.tao.datat.service.database.DatabaseTemplateService;
import com.tao.datat.service.database.Table;
import com.tao.datat.service.database.TableField;
import com.tao.datat.service.databaseinfo.DatabaseInfoService;
import com.tao.datat.service.databaseinfo.dto.TableQueryReq;
import com.tao.generator.standard.GeneratorService;
import com.tao.generator.standard.dto.*;
import com.tao.generator.standard.engine.FreemarkerTemplateEngine;
import com.tao.generator.standard.enums.FieldTypeEnum;
import com.tao.generator.standard.enums.QueryConditionEnum;
import com.tao.generator.standard.template.GeneratorGetDto;
import com.tao.generator.standard.template.GeneratorModel;
import com.tao.generator.standard.template.GeneratorReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tao.commons.utils.ClassUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author：cboss
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {
    private final static String JAVA_ENTITY_TEMPLATE = "\\java\\entity\\Entity.java.ftl";
    private final static String JAVA_MAPPER_TEMPLATE = "\\java\\mapper\\Mapper.java.ftl";
    private final static String JAVA_CUSTOM_MAPPER_TEMPLATE = "\\java\\mapper\\custom\\CustomMapper.java.ftl";
    private final static String XML_DEFAULT_MAPPER_TEMPLATE = "\\resources\\mybatis\\default\\mapper.xml.ftl";
    private final static String XML_CUSTOM_MAPPER_TEMPLATE = "\\resources\\mybatis\\custom\\customMapper.xml.ftl";
    private final DatabaseInfoService databaseInfoService;
    private final DatabaseTemplateService databaseTemplateService;

    /**
     * 生成所有文件
     *
     * @param req
     * @throws Exception
     */
    @Override
    public void generatorCodeAll(GeneratorReq req) throws Exception {
        List<Table> tables = databaseInfoService.queryTableInfo(BeanUtils.copyBean(req, TableQueryReq.class));
        if (tables.size() != 1) {
            throw new RuntimeException("数据库表数量不一致");
        }
        Table table = tables.get(0);
        Table tableEntity = ObjectUtils.deepClone(table, Table.class);
        //转换Table
        transformTable(req, tableEntity);

        GeneratorModel generatorModel = GeneratorModel.builder()
                .packageName(req.getPackageName())
                .servicePackageName(req.getPackageName() + ".service." + tableEntity.getNameLower())
                .entityClassName(tableEntity.getNameUpperCamelCase())
                .mapperClassName(tableEntity.getNameUpperCamelCase() + "Mapper")
                .customMapperClassName("Custom" + tableEntity.getNameUpperCamelCase() + "Mapper")
                .mapperXmlName(tableEntity.getNameUpperCamelCase() + "Mapper")
                .customMapperXmlName("Custom" + tableEntity.getNameUpperCamelCase() + "Mapper")
                .serviceClassName(tableEntity.getNameUpperCamelCase() + "Service")
                .controllerClassName(tableEntity.getNameUpperCamelCase() + "Controller")
                .author(req.getAuthor())
                .date(DateUtils.getDate(new Date()))
                .table(tableEntity)
                .build();
        //生成实体JAVA类
        generatorCodeJavaEntity(req, generatorModel);
        //生成Mapper.java类
        generatorCodeJavaMapper(req, generatorModel);
        //生成Mapper.xml文件
        generatorCodeXmlMapper(req, generatorModel);
        //生成定制Mapper.java类
        generatorCodeJavaCustomMapper(req, generatorModel);
        //生成定制Mapper.xml文件
        generatorCodeXmlCustomMapper(req, generatorModel);
        //生成DTO.java文件
        generatorCodeJavaDto(req, generatorModel);
        //生成service.java文件
        generatorCodeJavaService(req, generatorModel);
        //生成serviceImpl.java文件
        generatorCodeJavaServiceImpl(req, generatorModel);
        //生成controller.java文件
        generatorCodeJavaController(req, generatorModel);

    }

    @Override
    public String generatorReqDTO(GeneratorReqDTO req) throws Exception {
        List<TableField> tableFields = databaseTemplateService.sqlMetaData(req.getDs(), req.getSql());

        for (TableField field : tableFields) {
            field.setDataTypeNameJava(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getClassName());
            field.setNameLowerCamelCase(StringUtils.lowerCamelCase(field.getName()));
        }

        GeneratorReqDto generatorReqDto = GeneratorReqDto.builder()
                .packageName(req.getPackageName())
                .className(req.getClassName())
                .author(req.getAuthor())
                .date(DateUtils.getDate(new Date()))
                .fields(tableFields)
                .build();

        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorReqDto);
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + req.getPackageName().replace(".", "\\")
                + "\\" + generatorReqDto.getClassName() + ".java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\RspDTO.java.ftl", outputFullPathName);
        String fileContent = FileUtils.readText(outputFullPathName);
        return fileContent;
    }

    /**
     * 生成单对象查询
     *
     * @param req
     * @return
     */
    @Override
    public String generatorCodeGet(GeneratorGetReq req) throws Exception {
        List<TableField> tableFields = databaseTemplateService.sqlMetaData(req.getDs(), req.getSql());
        for (TableField field : tableFields) {
            field.setDataTypeNameJava(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getClassName());
            field.setNameLowerCamelCase(StringUtils.lowerCamelCase(field.getName()));
        }
        GeneratorGetDto generatorDto = BeanUtils.copyBean(req, GeneratorGetDto.class);
        generatorDto.setFields(tableFields);
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorDto);
        objectMap.put("date", DateUtils.getDate(new Date()));
        String mapperClassJavaName = req.getMapperClassName().substring(req.getMapperClassName().lastIndexOf(".") + 1);
        objectMap.put("mapperClassNameLower", StringUtils.lowerFirst(mapperClassJavaName));
        String serviceClassJavaName = req.getServiceClassName().substring(req.getServiceClassName().lastIndexOf(".") + 1);
        objectMap.put("serviceClassNameLower", StringUtils.lowerFirst(serviceClassJavaName));
        String servicePackageName = req.getServiceClassName().substring(0, req.getServiceClassName().lastIndexOf("."));
        objectMap.put("servicePackageName", servicePackageName);

        //生成Rsp.java
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "Rsp.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\get\\service\\dto\\RspDTO.java.ftl", outputFullPathName);

        //生成Model.java
        outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "Model.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\get\\service\\dto\\ModelDTO.java.ftl", outputFullPathName);

        //Service.java增加方法
        String serviceFile = req.getOutputPath()
                + "\\java\\" + req.getServiceClassName().replace(".", "\\")
                + ".java";
        String serviceContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\get\\service\\Service.java.ftl");
        ClassUtils.addMethod(serviceFile, serviceContent);

        //生成ServiceImpl.java
        String serviceImplFile = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\impl\\" + serviceClassJavaName + "Impl.java";
        String serviceImplContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\get\\service\\impl\\ServiceImpl.java.ftl");
        ClassUtils.addMethod(serviceImplFile, serviceImplContent);

        //生成Controlller.java
        String controllerFile = req.getOutputPath()
                + "\\java\\" + req.getControllerClassName().replace(".", "\\")
                + ".java";
        String controllerContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\get\\controller\\Controller.java.ftl");
        ClassUtils.addMethod(controllerFile, controllerContent);

        //生成CustomMapper.java
        String mapperJavaFile = req.getOutputPath()
                + "\\java\\" + req.getMapperClassName().replace(".", "\\")
                + ".java";
        String mapperJavaContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\get\\mapper\\custom\\CustomMapper.java.ftl");
        ClassUtils.addMethod(mapperJavaFile, mapperJavaContent);

        //生成CustomMapper.xml
        String mapperXmlFile = req.getOutputPath()
                + "\\resources\\mybatis\\custom\\"
                + mapperClassJavaName + ".xml";
        String mapperXmlContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\get\\mybatis\\custom\\customMapper.xml.ftl");
        XmlUtils.addMethod(mapperXmlFile, mapperXmlContent);
        return null;
    }

    /**
     * {
     *   "author": "",
     *   "conditionList": [
     *     {
     *       "fieldName": "example_id",
     *       "name": "exampleId",
     *       "value": "",
     *       "comment": "ID",
     *       "dataTypeName": "BIGINT",
     *       "dataTypeNameJava": "Long",
     *       "condition": "gt"
     *     },
     *     {
     *       "fieldName": "trade_id",
     *       "name": "tradeId",
     *       "value": "",
     *       "comment": "交易号",
     *       "dataTypeName": "BIGINT",
     *       "dataTypeNameJava": "Long",
     *       "condition": "lt"
     *     },
     *     {
     *       "fieldName": "order_no",
     *       "name": "orderNo",
     *       "value": "",
     *       "comment": "定单号",
     *       "dataTypeName": "VARCHAR",
     *       "dataTypeNameJava": "String",
     *       "condition": "equal"
     *     },
     *     {
     *       "fieldName": "int_abc",
     *       "name": "intAbc",
     *       "value": "",
     *       "comment": "",
     *       "dataTypeName": "INT",
     *       "dataTypeNameJava": "Integer",
     *       "condition": "gte"
     *     },
     *     {
     *       "fieldName": "smallint_abc",
     *       "name": "smallintAbc",
     *       "value": "",
     *       "comment": "",
     *       "dataTypeName": "SMALLINT",
     *       "dataTypeNameJava": "Short",
     *       "condition": "lte"
     *     },
     *     {
     *       "fieldName": "bigint_abc",
     *       "name": "bigintAbc",
     *       "value": "",
     *       "comment": "",
     *       "dataTypeName": "BIGINT",
     *       "dataTypeNameJava": "Long",
     *       "condition": "between"
     *     },
     *     {
     *       "fieldName": "create_time",
     *       "name": "createTime",
     *       "value": "",
     *       "comment": "记录创建时间",
     *       "dataTypeName": "TIMESTAMP",
     *       "dataTypeNameJava": "Date",
     *       "condition": "between_equal"
     *     },
     *     {
     *       "fieldName": "name",
     *       "name": "name",
     *       "value": "",
     *       "comment": "名称",
     *       "dataTypeName": "VARCHAR",
     *       "dataTypeNameJava": "String",
     *       "condition": "like"
     *     },
     *     {
     *       "fieldName": "type",
     *       "name": "type",
     *       "value": "",
     *       "comment": "",
     *       "dataTypeName": "VARCHAR",
     *       "dataTypeNameJava": "String",
     *       "condition": "include"
     *     }
     *   ],
     *   "controllerClassName": "com.tao.space.controller.ExampleNiceController",
     *   "description": "订单列表",
     *   "ds": {
     *     "driverClassName": "com.mysql.cj.jdbc.Driver",
     *     "password": "ea8dc0d1fe7e4073ad0296ea2eacc651",
     *     "url": "jdbc:mysql://localhost:3306/mybatis_example?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false&serverTimezone=Asia/Shanghai&&allowPublicKeyRetrieval=true",
     *     "username": "root"
     *   },
     *   "dtoName": "ExampleNiceOrderList",
     *   "mapperClassName": "com.tao.space.mapper.custom.CustomExampleNiceMapper",
     *   "methodName": "listOrder",
     *   "outputPath": "D:\\workspace\\my\\taoHelp\\src\\main",
     *   "serviceClassName": "com.tao.space.service.examplenice.ExampleNiceService",
     *   "sql": "select * from example_nice"
     * }
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public String generatorCodeList(GeneratorListReq req) throws Exception {
        List<TableField> tableFields = databaseTemplateService.sqlMetaData(req.getDs(), req.getSql());
        for (TableField field : tableFields) {
            field.setDataTypeNameJava(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getClassName());
            field.setNameLowerCamelCase(StringUtils.lowerCamelCase(field.getName()));
        }
        GeneratorGetDto generatorDto = BeanUtils.copyBean(req, GeneratorGetDto.class);
        generatorDto.setFields(tableFields);
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorDto);
        List<QueryCondition> conditionDtoList = new ArrayList<>();
        for (QueryCondition queryCondition : req.getConditionList()) {
            if (QueryConditionEnum.BETWEEN.getCode().equalsIgnoreCase(queryCondition.getCondition())
                    || QueryConditionEnum.BETWEEN_EQUAL.getCode().equalsIgnoreCase(queryCondition.getCondition())) {
                QueryCondition queryConditionStart = BeanUtils.copyBean(queryCondition, QueryCondition.class);
                queryConditionStart.setName(queryCondition.getName() + "Start");
                conditionDtoList.add(queryConditionStart);
                QueryCondition queryConditionEnd = BeanUtils.copyBean(queryCondition, QueryCondition.class);
                queryConditionEnd.setName(queryCondition.getName() + "End");
                conditionDtoList.add(queryConditionEnd);
            } else if (QueryConditionEnum.INCLUDE.getCode().equalsIgnoreCase(queryCondition.getCondition())) {
                QueryCondition queryConditionList = BeanUtils.copyBean(queryCondition, QueryCondition.class);
                queryConditionList.setName(queryCondition.getName() + "List");
                queryConditionList.setDataTypeNameJava("List<" + queryCondition.getDataTypeNameJava() + ">");
                conditionDtoList.add(queryConditionList);
            } else {
                conditionDtoList.add(queryCondition);
            }
        }
        objectMap.put("conditionListDto", conditionDtoList);
        objectMap.put("conditionList", req.getConditionList());
        objectMap.put("date", DateUtils.getDate(new Date()));
        String mapperClassJavaName = req.getMapperClassName().substring(req.getMapperClassName().lastIndexOf(".") + 1);
        objectMap.put("mapperClassNameLower", StringUtils.lowerFirst(mapperClassJavaName));
        String serviceClassJavaName = req.getServiceClassName().substring(req.getServiceClassName().lastIndexOf(".") + 1);
        objectMap.put("serviceClassNameLower", StringUtils.lowerFirst(serviceClassJavaName));
        String servicePackageName = req.getServiceClassName().substring(0, req.getServiceClassName().lastIndexOf("."));
        objectMap.put("servicePackageName", servicePackageName);


        //生成Req.java
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "Req.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\list\\service\\dto\\Req.java.ftl", outputFullPathName);

        //生成DaoReq.java
        outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "DaoReq.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\list\\service\\dto\\DaoReq.java.ftl", outputFullPathName);

        //生成Rsp.java
        outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "Rsp.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\list\\service\\dto\\RspDTO.java.ftl", outputFullPathName);

        //生成Model.java
        outputFullPathName = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\dto\\" + req.getDtoName() + "Model.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\sql\\list\\service\\dto\\ModelDTO.java.ftl", outputFullPathName);

        //Service.java增加方法
        String serviceFile = req.getOutputPath()
                + "\\java\\" + req.getServiceClassName().replace(".", "\\")
                + ".java";
        String serviceContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\list\\service\\Service.java.ftl");
        ClassUtils.addMethod(serviceFile, serviceContent);

        //生成ServiceImpl.java
        String serviceImplFile = req.getOutputPath()
                + "\\java\\" + servicePackageName.replace(".", "\\")
                + "\\impl\\" + serviceClassJavaName + "Impl.java";
        String serviceImplContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\list\\service\\impl\\ServiceImpl.java.ftl");
        ClassUtils.addMethod(serviceImplFile, serviceImplContent);

        //生成Controlller.java
        String controllerFile = req.getOutputPath()
                + "\\java\\" + req.getControllerClassName().replace(".", "\\")
                + ".java";
        String controllerContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\list\\controller\\Controller.java.ftl");
        ClassUtils.addMethod(controllerFile, controllerContent);

        //生成CustomMapper.java
        String mapperJavaFile = req.getOutputPath()
                + "\\java\\" + req.getMapperClassName().replace(".", "\\")
                + ".java";
        String mapperJavaContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\list\\mapper\\custom\\CustomMapper.java.ftl");
        ClassUtils.addMethod(mapperJavaFile, mapperJavaContent);

        //生成CustomMapper.xml
        String mapperXmlFile = req.getOutputPath()
                + "\\resources\\mybatis\\custom\\"
                + mapperClassJavaName + ".xml";
        String mapperXmlContent = FreemarkerTemplateEngine.generateStringFromTemplate(objectMap,
                "\\java\\sql\\list\\mybatis\\custom\\customMapper.xml.ftl");
        XmlUtils.addMethod(mapperXmlFile, mapperXmlContent);
        return null;
    }

    /**
     * 解析SQL语句输出查询条件
     *
     * @param req
     * @return
     */
    @Override
    public List<QueryCondition> generatorQueryCondition(GeneratorQueryConditionReq req) throws Exception {
        List<TableField> tableFields = databaseTemplateService.sqlMetaData(req.getDs(), req.getSql());
        return tableFields.stream().map(field -> {
            QueryCondition condition = new QueryCondition();
            condition.setFieldName(field.getName());
            condition.setName(StringUtils.lowerCamelCase(field.getName()));
            condition.setCondition(QueryConditionEnum.EQUAL.getCode());
            condition.setComment(field.getComment());
            condition.setDataTypeName(field.getDataTypeName());
            condition.setDataTypeNameJava(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getClassName());
            condition.setValue("");
            return condition;
        }).collect(Collectors.toList());
    }

    @Override
    public void generatorCodeMapperAndEntityDTO(GeneratorReq req) throws Exception {
        List<Table> tables = databaseInfoService.queryTableInfo(BeanUtils.copyBean(req, TableQueryReq.class));
        if (tables.size() != 1) {
            throw new RuntimeException("数据库表数量不一致");
        }
        Table table = tables.get(0);
        Table tableEntity = ObjectUtils.deepClone(table, Table.class);
        //转换Table
        transformTable(req, tableEntity);

        GeneratorModel generatorModel = GeneratorModel.builder()
                .packageName(req.getPackageName())
                .servicePackageName(req.getPackageName() + ".service." + tableEntity.getNameLower())
                .entityClassName(tableEntity.getNameUpperCamelCase())
                .mapperClassName(tableEntity.getNameUpperCamelCase() + "Mapper")
                .customMapperClassName("Custom" + tableEntity.getNameUpperCamelCase() + "Mapper")
                .mapperXmlName(tableEntity.getNameUpperCamelCase() + "Mapper")
                .customMapperXmlName("Custom" + tableEntity.getNameUpperCamelCase() + "Mapper")
                .serviceClassName(tableEntity.getNameUpperCamelCase() + "Service")
                .controllerClassName(tableEntity.getNameUpperCamelCase() + "Controller")
                .author(req.getAuthor())
                .date(DateUtils.getDate(new Date()))
                .table(tableEntity)
                .build();
        //生成实体JAVA类
        generatorCodeJavaEntity(req, generatorModel);
        //生成Mapper.java类
        generatorCodeJavaMapper(req, generatorModel);
        //生成Mapper.xml文件
        generatorCodeXmlMapper(req, generatorModel);
        //生成DTO.java文件
        generatorCodeJavaDto(req, generatorModel);

    }

    private void generatorCodeJavaController(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + req.getPackageName().replace(".", "\\")
                + "\\controller\\" + generatorModel.getTable().getNameUpperCamelCase()
                + "Controller.java";
        FreemarkerTemplateEngine.writer(objectMap, "\\java\\controller\\Controller.java.ftl", outputFullPathName);
    }

    private void generatorCodeJavaServiceImpl(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        String[] files = {"ServiceImpl.java", "ManagerServiceImpl.java"};
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        for (String dtoFile : files) {
            String outputFullPathName = req.getOutputPath()
                    + "\\java\\" + req.getPackageName().replace(".", "\\")
                    + "\\service\\" + generatorModel.getTable().getNameLower()
                    + "\\impl\\" + generatorModel.getTable().getNameUpperCamelCase()
                    + dtoFile;
            FreemarkerTemplateEngine.writer(objectMap, "\\java\\service\\impl\\" + dtoFile + ".ftl", outputFullPathName);
        }
    }

    private void generatorCodeJavaService(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        String[] files = {"Service.java", "ManagerService.java"};
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        for (String dtoFile : files) {
            String outputFullPathName = req.getOutputPath()
                    + "\\java\\" + req.getPackageName().replace(".", "\\")
                    + "\\service\\" + generatorModel.getTable().getNameLower()
                    + "\\" + generatorModel.getTable().getNameUpperCamelCase()
                    + dtoFile;
            FreemarkerTemplateEngine.writer(objectMap, "\\java\\service\\" + dtoFile + ".ftl", outputFullPathName);
        }
    }

    private void generatorCodeJavaDto(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        String[] dtoFiles = {"CacheRsp.java", "InsertReq.java", "ListDaoReq.java"
                , "ListModel.java", "ListReq.java", "ListRsp.java", "Rsp.java", "UpdateReq.java"};
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        for (String dtoFile : dtoFiles) {
            String outputFullPathName = req.getOutputPath()
                    + "\\java\\" + req.getPackageName().replace(".", "\\")
                    + "\\service\\" + generatorModel.getTable().getNameLower()
                    + "\\dto\\" + generatorModel.getTable().getNameUpperCamelCase()
                    + dtoFile;
            FreemarkerTemplateEngine.writer(objectMap, "\\java\\service\\dto\\" + dtoFile + ".ftl", outputFullPathName);
        }
    }

    private void generatorCodeXmlCustomMapper(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\resources\\mybatis\\custom\\"
                + generatorModel.getCustomMapperXmlName() + ".xml";
        FreemarkerTemplateEngine.writer(objectMap, XML_CUSTOM_MAPPER_TEMPLATE, outputFullPathName);
    }

    private void generatorCodeJavaCustomMapper(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + req.getPackageName().replace(".", "\\")
                + "\\mapper\\custom\\" + generatorModel.getCustomMapperClassName() + ".java";
        FreemarkerTemplateEngine.writer(objectMap, JAVA_CUSTOM_MAPPER_TEMPLATE, outputFullPathName);
    }

    /**
     * 生成Mapper.xml文件
     *
     * @param req
     * @param generatorModel
     */
    private void generatorCodeXmlMapper(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\resources\\mybatis\\default\\"
                + generatorModel.getMapperXmlName() + ".xml";
        FreemarkerTemplateEngine.writer(objectMap, XML_DEFAULT_MAPPER_TEMPLATE, outputFullPathName);
    }

    /**
     * 生成Mapper.java类
     *
     * @param req
     * @param generatorModel
     * @throws Exception
     */
    private void generatorCodeJavaMapper(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + req.getPackageName().replace(".", "\\")
                + "\\mapper\\" + generatorModel.getMapperClassName() + ".java";
        FreemarkerTemplateEngine.writer(objectMap, JAVA_MAPPER_TEMPLATE, outputFullPathName);
    }

    /**
     * 转换Table
     *
     * @param req
     * @param table
     */
    private void transformTable(GeneratorReq req, Table table) {
        table.setPrimaryKeyName(req.getPrimaryKeyName());
        table.setPrimaryKeyNameLowerCamelCase(StringUtils.lowerCamelCase(req.getPrimaryKeyName()));
        table.setNameUpperCamelCase(StringUtils.upperCamelCase(table.getName()));
        table.setNameLowerCamelCase(StringUtils.lowerCamelCase(table.getName()));
        table.setNameLower(table.getNameLowerCamelCase().toLowerCase());
        table.setNameUpper(table.getNameUpperCamelCase().toUpperCase());

        List<TableField> fieldsNotPrimaryKey = new ArrayList<>();
        for (TableField field : table.getFields()) {
            field.setDataTypeNameJava(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getClassName());
            field.setDataTypeNameMapperXml(FieldTypeEnum.getEnumBySqlType(field.getDataType()).getMapperXmlName());
            field.setNameLowerCamelCase(StringUtils.lowerCamelCase(field.getName()));
            field.setIsPrimaryKey((Objects.nonNull(req.getPrimaryKeyName()) && req.getPrimaryKeyName().equals(field.getName())) ? 1 : 2);
            if ((Objects.nonNull(req.getPrimaryKeyName()) && !req.getPrimaryKeyName().equals(field.getName()))) {
                fieldsNotPrimaryKey.add(field);
            }
//            log.info("field:{}", field);
        }
        table.setFieldsNotPrimaryKey(fieldsNotPrimaryKey);
    }

    /**
     * 生成实体JAVA类
     *
     * @param req
     * @param generatorModel
     * @throws Exception
     */
    public void generatorCodeJavaEntity(GeneratorReq req, GeneratorModel generatorModel) throws Exception {
        Map<String, Object> objectMap = ObjectUtils.convertPojoToHashMap(generatorModel);
        String outputFullPathName = req.getOutputPath()
                + "\\java\\" + req.getPackageName().replace(".", "\\")
                + "\\entity\\" + generatorModel.getEntityClassName() + ".java";
        FreemarkerTemplateEngine.writer(objectMap, JAVA_ENTITY_TEMPLATE, outputFullPathName);
    }
}