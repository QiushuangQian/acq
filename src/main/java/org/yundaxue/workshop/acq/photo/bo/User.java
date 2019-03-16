package org.yundaxue.workshop.acq.photo.bo;

/**
 * Created by lenovo on 2019/3/16.
 */
//用户实体类
public class User {
    private int userId;
    private String email;   //用户邮箱
    private String password;//用户密码
    private int spaceCapacity;//空间容量，以B为单位
    private int usedCapacity;//已用空间
    private int photoNum;  //照片总数
    private String openId;  //绑定的微信
    private String qqNum;  //绑定的QQ

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
}
