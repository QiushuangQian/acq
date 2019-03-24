package org.yundaxue.workshop.acq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yundaxue.workshop.acq.exception.CatException;
import org.yundaxue.workshop.acq.model.Mapper.UserMapper;
import org.yundaxue.workshop.acq.model.User;
import org.yundaxue.workshop.acq.service.MailService;
import org.yundaxue.workshop.acq.service.UserService;
import org.yundaxue.workshop.acq.util.Util;

/**
 * Created by lenovo on 2019/3/17.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Override
    public int insertUser(User user) throws Exception {
        return userMapper.insertUser(user);
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
    public User getUser(int userId) throws Exception {
        return userMapper.getUser(userId);
    }

    //将注册的用户信息保存在数据库，状态设为未激活
    public Boolean register(User user){
        if (userMapper.getUserByEmail(user.getEmail()) != null ){
            return false;
        }

        //设置状态码
        user.setState(2);
        //设置激活码
        user.setIdCode(Util.getUUID());

        //将用户信息存入数据库
        int i= userMapper.insertUser(user);

        //发送一封激活邮件
        mailService.sendMail(user.getEmail(),user.getIdCode());

        if (i == 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean sendMailChangePsw(User user) {
        if (userMapper.getUserByEmail(user.getEmail()) == null ){
            return false;
        }

        //设置状态码
        user.setState(2);
        //设置激活码
        user.setIdCode(Util.getUUID());

        //将用户信息更新到数据库
        int i= userMapper.updateUser(user);

        //发送一封激活邮件
        mailService.sendMail(user.getEmail(),user.getIdCode());

        if (i == 1){
            return true;
        }
        return false;
    }


    //激活用户
    public Boolean activeUser(String idCode){
        User user = userMapper.selectUserByIdCode(idCode);
        if(user != null){
            user.setState(1);
            user.setIdCode(null);
            userMapper.updateUser(user);
            return true;
        }else {
            return false;
        }
    }

    public boolean login(User user) throws CatException {
        if (user.getUserId()==0) {
            throw new CatException(CatException.USER_EMPTY, "用户为空");
        }
        User u1= userMapper.getUser(user.getUserId());

        if (u1 == null)  {
            throw new CatException(CatException.USER_NOT_EXISTS, "用户不存在");
        }

        if (! u1.getPassword().equals(user.getPassword())) {
            throw new CatException(CatException.PASSWORD_ERROR, "用户或密码不匹配");
        }

        return true;
    }
}
