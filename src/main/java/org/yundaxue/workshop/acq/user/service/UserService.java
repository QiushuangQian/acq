package org.yundaxue.workshop.acq.user.service;

/**
 * Created by lenovo on 2019/3/16.
 */
public interface UserService {
    //增加
    void insertUser(String email,String password) throws Exception;
    void insertUser(String email,String password,String openId) throws Exception;
    void insertUser(String email,String password,String openId,String qqNum) throws Exception;

    //删除


    //查找


    //修改
}
