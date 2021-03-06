package org.yundaxue.workshop.acq.model.Mapper;

import org.apache.ibatis.annotations.*;
import org.yundaxue.workshop.acq.model.User;

/**
 * Created by lenovo on 2019/3/17.
 */
@Mapper
public interface UserMapper {
    //插入用户
    @Insert("insert into user (email,password,state,id_code) " +
            "values(#{user.email},#{user.password},#{user.state},#{user.idCode})")
    public int insertUser(@Param("user") User user);

    //根据激活码查找用户
    @Select("select * from user where id_code = #{idCode}")
    public User selectUserByIdCode(String idCode);

    //根据用户邮箱更新用户状态和idCode和密码
    @Update("update user set state=#{user.state},id_code=#{user.idCode},password=#{user.password} where email=#{user.email}")
    public int updateUser(@Param("user") User user);

    //根据userId删除用户
    @Delete("delete from user where user_id = #{userId}")
    public void deleteUser(int userId);

    //修改
    //修改用户密码
    @Update("update user set password = #{password} where user_id = #{userId}")
    public void updatePassword(@Param("userId") int userId, @Param("password") String password);
    //修改用户空间容量
    @Update("update user set space_capacity = #{spaceCapacity} where user_id = #{userId}")
    public void updateSpaceCapacity(@Param("userId") int userId, @Param("spaceCapacity") int spaceCapacity);
    //修改用户已用空间
    @Update("update user set used_capacity = #{usedCapacity} where user_id = #{userId}")
    public void updateUsedCapacity(@Param("userId") int userId, @Param("usedCapacity") int usedCapacity);
    //修改照片总数
    @Update("update user set photo_num = #{photoNum} where user_id = #{userId}")
    public void updatePhotoNum(@Param("userId") int userId, @Param("photoNum") int photoNum);
    //修改绑定的微信账号
    @Update("update user set wechat = #{wechat} where user_id = #{userId}")
    public void updateWechat(@Param("userId") int userId, @Param("wechat") String wechat);
    //修改绑定的QQ账号
    @Update("update user set qq = #{qq} where user_id = #{userId}")
    public void updateQq(@Param("userId") int userId, @Param("qq") String qq);

    //查找用户
    @Select("select * from user where user_id = #{userId}")
    public User getUser(int userId);


    //根据email查找用户
    @Select("select * from user where email = #{email}")
    public User getUserByEmail(String email);

}
