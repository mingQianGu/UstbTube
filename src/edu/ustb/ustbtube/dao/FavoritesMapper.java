package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.Favorites;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface FavoritesMapper {

    @Select("select count(1) from favorites where UserId=#{userId} and VideoId=#{videoId}")
    public int selectIsFavorites(Favorites favorites);

    @Insert("insert into favorites (Id, UserId, VideoId) " +
            "values (#{id}, #{userId}, #{videoId})")
    public int insertFavorites(Favorites favorites);

    @Delete("delete from favorites where UserId=#{userId} and VideoId=#{videoId}")
    public int deleteFavorites(Favorites favorites);
}
