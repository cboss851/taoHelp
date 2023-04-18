package com.tao.space.service.user.impl;

import com.tao.commons.redisson.RedisLock;
import com.tao.commons.utils.BeanUtils;
import com.tao.space.mapper.SysUserMapper;
import com.tao.space.entity.SysUser;
import com.tao.space.service.user.UserManagerService;
import com.tao.space.service.user.dto.UserCacheRsp;
import com.tao.space.service.user.dto.UserUpdateReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户
 *
 * @Author：cboss
 * @Date：2023/4/4
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagerServiceImpl implements UserManagerService {
    public static final String REDISSON_LOCK_USER_UPDATE_USER_ID = "USER:UPDATE:LOCK:{userId}";
    public static final String REDIS_KEY_USER_ID = "user:{userId}";
    private final SysUserMapper sysUserMapper;
    private final RedisTemplate redisTemplate;

    @Override
    public UserCacheRsp getUserCache(Long userId) {
        String cacheKey = REDIS_KEY_USER_ID.replace("{userId}", String.valueOf(userId));
        UserCacheRsp cacheRsp = (UserCacheRsp) redisTemplate.opsForValue().get(cacheKey);
        if (Objects.isNull(cacheRsp)) {
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
            cacheRsp = BeanUtils.copyBean(sysUser, UserCacheRsp.class);
            redisTemplate.opsForValue().set(cacheKey, cacheRsp, 12, TimeUnit.HOURS);
        }
        return cacheRsp;
    }

    @RedisLock(key = REDISSON_LOCK_USER_UPDATE_USER_ID)
    @Override
    public void updateDistributedLock(UserUpdateReq req) {
        log.info("start");
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end");
    }
}