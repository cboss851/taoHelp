package com.tao.space.service.examplenice.impl;

import com.tao.commons.redisson.RedisLock;
import com.tao.commons.utils.BeanUtils;
import com.tao.space.mapper.ExampleNiceMapper;
import com.tao.space.entity.ExampleNice;
import com.tao.space.service.examplenice.ExampleNiceManagerService;
import com.tao.space.service.examplenice.dto.ExampleNiceCacheRsp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * 新建新表请拷贝这个样例模板
 *
 * @author cboss
 * @Date 2023-04-08
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleNiceManagerServiceImpl implements ExampleNiceManagerService {
    public static final String REDIS_KEY_EXAMPLENICE_ID = "examplenice:{exampleId}";
    private final ExampleNiceMapper exampleNiceMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public ExampleNiceCacheRsp getExampleNiceCache(Long exampleId) {
        String cacheKey = REDIS_KEY_EXAMPLENICE_ID.replace("{exampleId}", String.valueOf(exampleId));
        ExampleNiceCacheRsp cacheRsp = (ExampleNiceCacheRsp) redisTemplate.opsForValue().get(cacheKey);
        if (Objects.isNull(cacheRsp)) {
            ExampleNice ExampleNice = exampleNiceMapper.selectByPrimaryKey(exampleId);
            cacheRsp = BeanUtils.copyBean(ExampleNice, ExampleNiceCacheRsp.class);
            redisTemplate.opsForValue().set(cacheKey, cacheRsp, 12, TimeUnit.HOURS);
        }
        return cacheRsp;
    }
}