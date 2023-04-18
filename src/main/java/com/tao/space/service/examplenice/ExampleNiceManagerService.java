package com.tao.space.service.examplenice;

import com.tao.space.service.examplenice.dto.ExampleNiceCacheRsp;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
public interface ExampleNiceManagerService {

    ExampleNiceCacheRsp getExampleNiceCache(Long exampleId);
}
