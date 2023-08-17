package ${packageName}.service.${table.nameLower}.impl;

import com.tao.commons.redisson.RedisLock;
import com.tao.commons.utils.BeanUtils;
import ${packageName}.mapper.${table.nameUpperCamelCase}Mapper;
import ${packageName}.entity.${table.nameUpperCamelCase};
import ${packageName}.service.${table.nameLower}.${table.nameUpperCamelCase}ManagerService;
import ${packageName}.service.${table.nameLower}.dto.${table.nameUpperCamelCase}CacheRsp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *
 * ${table.comment!}
 *
 * @author ${author}
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class ${table.nameUpperCamelCase}ManagerServiceImpl implements ${table.nameUpperCamelCase}ManagerService {
    public static final String REDIS_KEY_${table.nameUpper}_ID = "${table.nameLower}:{${table.primaryKeyNameLowerCamelCase}}";
    private final ${table.nameUpperCamelCase}Mapper ${table.nameLowerCamelCase}Mapper;
    private final RedisTemplate redisTemplate;

    @Override
    public ${table.nameUpperCamelCase}CacheRsp get${table.nameUpperCamelCase}Cache(Long ${table.primaryKeyNameLowerCamelCase}) {
        String cacheKey = REDIS_KEY_${table.nameUpper}_ID.replace("{${table.primaryKeyNameLowerCamelCase}}", String.valueOf(${table.primaryKeyNameLowerCamelCase}));
        ${table.nameUpperCamelCase}CacheRsp cacheRsp = (${table.nameUpperCamelCase}CacheRsp) redisTemplate.opsForValue().get(cacheKey);
        if (Objects.isNull(cacheRsp)) {
            ${table.nameUpperCamelCase} ${table.nameUpperCamelCase} = ${table.nameLowerCamelCase}Mapper.selectByPrimaryKey(${table.primaryKeyNameLowerCamelCase});
            cacheRsp = BeanUtils.copyBean(${table.nameUpperCamelCase}, ${table.nameUpperCamelCase}CacheRsp.class);
            redisTemplate.opsForValue().set(cacheKey, cacheRsp, 12, TimeUnit.HOURS);
        }
        return cacheRsp;
    }
}