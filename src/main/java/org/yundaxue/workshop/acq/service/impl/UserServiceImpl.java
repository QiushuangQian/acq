package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.dao.Mapper.UserMapper;
import org.yundaxue.workshop.acq.dao.UserDao;
import org.yundaxue.workshop.acq.service.UserService;

/**
 * Created by lenovo on 2019/3/17.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void insertUser(String email, String password) throws Exception {
        userMapper.insertUser(email,password);
    }

    @Override
    public void deleteUser(int userId) throws Exception {
        userMapper.deleteUser(userId);
    }

    @Override
    public void updatePassword(int userId, String password) throws Exception {
        userMapper.updatePassword(userId,password);
    }

    @Override
    public void updateSpaceCapacity(int userId, int spaceCapacity) throws Exception {
        userMapper.updateSpaceCapacity(userId,spaceCapacity);
    }

    @Override
    public void updateUsedCapacity(int userId, int usedCapacity) throws Exception {
        userMapper.updateUsedCapacity(userId,usedCapacity);
    }

    @Override
    public void updatePhotoNum(int userId, int photoNum) throws Exception {
        userMapper.updatePhotoNum(userId,photoNum);
    }

    @Override
    public void updateWechat(int userId, String wechat) throws Exception {
        userMapper.updateWechat(userId,wechat);
    }

    @Override
    public void updateQq(int userId, String qq) throws Exception {
        userMapper.updateQq(userId,qq);
    }

    @Override
    public UserDao gerUser(int userId) throws Exception {
        return userMapper.getUser(userId);
    }
}