package com.tao.space.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tao.commons.result.ResponseResultPage;
import com.tao.commons.utils.BeanUtils;
import com.tao.space.mapper.SysUserMapper;
import com.tao.space.mapper.custom.CustomSysUserMapper;
import com.tao.space.entity.SysUser;
import com.tao.space.service.user.UserManagerService;
import com.tao.space.service.user.UserService;
import com.tao.space.service.user.dto.*;
import com.tao.space.service.user.dto.UserListModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 *
 * @Author：cboss
 * @Date：2023/3/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final SysUserMapper sysUserMapper;
    private final CustomSysUserMapper customSysUserMapper;
    private final UserManagerService userManagerService;

    @Override
    public void insert(UserInsertReq req) {
        SysUser sysUser = BeanUtils.copyBean(req, SysUser.class);
        sysUser.setStatus(Short.valueOf("1"));
        sysUser.setIsDelete(Short.valueOf("2"));
        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void update(UserUpdateReq req) {
        SysUser sysUser = BeanUtils.copyBean(req, SysUser.class);
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void delete(Long id) {
        sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ResponseResultPage<UserListRsp> list(UserListReq req) {
        Page<UserListRsp> page = PageHelper.startPage(req.getPage(), req.getSize());

        UserListDaoReq daoReq = BeanUtils.copyBean(req, UserListDaoReq.class);
        List<UserListModel> userListModelList = customSysUserMapper.queryList(daoReq);

        List<UserListRsp> userListRspList = BeanUtils.copyBeanList(userListModelList, UserListRsp.class);
        return ResponseResultPage.page(page.getTotal(), userListRspList);
    }

    @Override
    public UserRsp getUser(Long userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        return BeanUtils.copyBean(sysUser, UserRsp.class);
    }

    @Override
    public UserRsp getUserCache(Long userId) {
        UserCacheRsp userCacheRsp = userManagerService.getUserCache(userId);
        return BeanUtils.copyBean(userCacheRsp, UserRsp.class);
    }

    @Override
    public void updateDistributedLock(UserUpdateReq req) {
        userManagerService.updateDistributedLock(req);
    }
}