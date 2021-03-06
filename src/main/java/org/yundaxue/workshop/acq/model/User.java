package org.yundaxue.workshop.acq.model;

import lombok.Data;

/**
 * Created by lenovo on 2019/3/16.
 */
//用户实体类

@Data
public class User {
    private int userId;
    private String email;   //用户邮箱
    private String password;//用户密码
    private int spaceCapacity;//空间容量，以B为单位
    private int usedCapacity;//已用空间
    private int photoNum;  //照片总数
    private String wechat;  //绑定的微信
    private String qq;  //绑定的QQ
    private String nickname;//昵称
    private int albumNum;//相册数

    //账户状态  0为封禁 1为正常 2为未激活
    private int state;
    //验证码
    private String idCode;
}
