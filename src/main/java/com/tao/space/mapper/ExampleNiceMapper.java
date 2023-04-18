package com.tao.space.mapper;

import com.tao.space.entity.ExampleNice;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
public interface ExampleNiceMapper {
    int deleteByPrimaryKey(Long exampleId);

    int insert(ExampleNice record);

    int insertSelective(ExampleNice record);

    ExampleNice selectByPrimaryKey(Long exampleId);

    int updateByPrimaryKeySelective(ExampleNice record);

    int updateByPrimaryKey(ExampleNice record);
}