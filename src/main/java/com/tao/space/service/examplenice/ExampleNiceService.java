package com.tao.space.service.examplenice;

import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.examplenice.dto.*;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
public interface ExampleNiceService {
    void insert(ExampleNiceInsertReq req);

    void update(ExampleNiceUpdateReq req);

    void delete(Long exampleId);

    ResponseResultPage<ExampleNiceListRsp> list(ExampleNiceListReq req);

    ExampleNiceRsp getExampleNice(Long exampleId);

    //订单列表
    ResponseResultPage<ExampleNiceOrderListRsp> listOrder(ExampleNiceOrderListReq req);
}