package com.tao.space.mapper.custom;

import com.tao.space.service.examplenice.dto.ExampleNiceListModel;
import com.tao.space.service.examplenice.dto.ExampleNiceListDaoReq;
import com.tao.space.service.examplenice.dto.ExampleNiceOrderListDaoReq;
import com.tao.space.service.examplenice.dto.ExampleNiceOrderListModel;

import java.util.List;

/**
* 新建新表请拷贝这个样例模板
*
* @author cboss
* @Date 2023-04-08
*/
public interface CustomExampleNiceMapper {
    List<ExampleNiceListModel> list(ExampleNiceListDaoReq req);
    //订单列表
    List<ExampleNiceOrderListModel> listOrder(ExampleNiceOrderListDaoReq req);
}