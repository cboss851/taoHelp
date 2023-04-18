package com.tao.space.mapper.custom;

import com.tao.space.service.user.dto.UserListDaoReq;
import com.tao.space.service.user.dto.UserListModel;

import java.util.List;

/**
 * @Author：cboss
 * @Date：2023/3/30
 */
public interface CustomSysUserMapper {
    List<UserListModel> queryList(UserListDaoReq req);
}