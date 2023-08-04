package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where UserId=#{userId}")
    public User getUserByUserId(String userId);

    @Select("select * from user where phone=#{phone}")
    public User getUserByPhone(String phone);

    @Select("select count(1) from user where NickName=#{nickName}")
    public int checkNickName(String nickName);

    @Insert("insert into user (UserId,PassWord,NickName,Phone,Mail,Permission,Img) " +
            "values (#{userId}, #{passWord}, #{nickName}, #{phone}, #{mail}, #{permission}, #{img})")
    public int insertUser(User user);

}
