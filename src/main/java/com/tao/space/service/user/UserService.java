package com.tao.space.service.user;

import com.tao.commons.result.ResponseResultPage;
import com.tao.space.service.user.dto.*;

/**
 * 用户服务类
 * @Author：cboss
 * @Date：2023/3/25
 */
public interface UserService {
    void insert(UserInsertReq req);

    void update(UserUpdateReq req);

    void delete(Long id);

    ResponseResultPage<UserListRsp> list(UserListReq req);

    UserRsp getUser(Long id);

    UserRsp getUserCache(Long id);

    void updateDistributedLock(UserUpdateReq req);
}
