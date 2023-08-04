package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.Star;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StarMapper {

    @Select("select count(1) from star where UserId=#{userId} and VideoId=#{videoId}")
    public int selectIsStar(Star star);

    @Insert("insert into star (Id, UserId, VideoId) " +
            "values (#{id}, #{userId}, #{videoId})")
    public int insertStar(Star star);

    @Delete("delete from star where UserId=#{userId} and VideoId=#{videoId}")
    public int deleteStar(Star star);
}
