package com.tao.space.service.user;

import com.tao.space.service.user.dto.UserCacheRsp;
import com.tao.space.service.user.dto.UserUpdateReq;

/**
 * @Author：cboss
 * @Date：2023/4/4
 */
public interface UserManagerService {

    UserCacheRsp getUserCache(Long id);

    void updateDistributedLock(UserUpdateReq req);
}
