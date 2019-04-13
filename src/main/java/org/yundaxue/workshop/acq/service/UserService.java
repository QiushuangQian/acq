package org.yundaxue.workshop.acq.service;

import org.yundaxue.workshop.acq.exception.CatException;
import org.yundaxue.workshop.acq.model.User;

/**
 * Created by lenovo on 2019/3/17.
 */
public interface UserService {
    //插入用户
    int insertUser(User user) throws Exception;

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
    User getUser(int userId)throws Exception;

    //激活用户
    Boolean activeUser(String IdCode);

    //将注册的用户信息保存在数据库，状态设为未激活
    Boolean register(User user);
    //将要修改密码的用户信息更新到数据库，状态设为未激活
    Boolean sendMailChangePsw(User user);

    User login(User user)  throws CatException;
}
