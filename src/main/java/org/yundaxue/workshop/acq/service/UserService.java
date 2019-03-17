package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.dao.UserDao;

/**
 * Created by lenovo on 2019/3/17.
 */
public interface UserService {
    //插入用户
    void insertUser(String email,String password) throws Exception;

    //删除用户
    void deleteUser(int userId) throws Exception;

    //修改
    //修改用户密码
    void updatePassword(int userId,String password) throws Exception;
    //修改用户空间容量
    void updateSpaceCapacity(int userId,int spaceCapacity)throws Exception;
    //修改用户已用空间
    void updateUsedCapacity(int userId,int usedCapacity)throws Exception;
    //修改照片总数
    void updatePhotoNum(int userId,int photoNum)throws Exception;
    //修改绑定的微信账号
    void updateWechat(int userId,String wechat)throws Exception;
    //修改绑定的QQ账号
    void updateQq(int userId,String qq)throws Exception;

    //查找用户
    UserDao gerUser(int userId)throws Exception;
}
