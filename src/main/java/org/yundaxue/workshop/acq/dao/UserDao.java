package org.yundaxue.workshop.acq.dao;

/**
 * Created by lenovo on 2019/3/16.
 */
//用户实体类
public class UserDao {
    private int userId;
    private String email;   //用户邮箱
    private String password;//用户密码
    private int spaceCapacity;//空间容量，以B为单位
    private int usedCapacity;//已用空间
    private int photoNum;  //照片总数
    private String wechat;  //绑定的微信
    private String qq;  //绑定的QQ

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSpaceCapacity() {
        return spaceCapacity;
    }

    public void setSpaceCapacity(int spaceCapacity) {
        this.spaceCapacity = spaceCapacity;
    }

    public int getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(int usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
