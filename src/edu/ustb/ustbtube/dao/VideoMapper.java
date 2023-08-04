package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.entity.Video;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

public interface VideoMapper {


    @Select("select * from video where VideoId=#{videoId}")
    public Video getVideoById(String videoId);

    @Select("select NickName from user where UserId=#{userId}")
    public String getUserName(Video video);

    @Select("select NickName from user where UserId=#{userId}")
    public  String getUserNameByUserId(@Param("userId") String id);

    @Select("select * from user where userid = #{userId}")
    public User getUserByUserId(String userId);

    @Select("select vi.* from favorites fav inner join video vi  on fav.videoid = vi.videoid where fav.userid = #{userId}")
    public List<Video> getFavoritesByUserId(String userId);

    @Select("select * from video where userid = #{userId} order by CreateTime desc")
    public List<Video> getVideoByUserId(String userId);

    @Select("select * from video order by ReadTimes desc limit 1")
    public Video getHottest();

    @Select("select count(1) from video")
    public int getVideoCount();

    @Select("select * from video order by rand() limit #{count}")
    public ArrayList<Video> getRand(int count);

    @Select("select * from video where Tag=#{video.tag} and VideoId!=#{video.videoId} order by rand() limit #{count}")
    public ArrayList<Video> getRandByTag(@Param("video") Video video, @Param("count") int count);

    @Select("select * from video order by CreateTime desc limit #{count}")
    public ArrayList<Video> getVideoByTime(int count);

    @Select("select * from video where Tag='资讯' order by CreateTime desc limit 3")
    public ArrayList<Video> getTopNews();

    @Insert("insert into video (VideoId,CreateTime,UserId,ReadTimes,Star,Favorites,Comment,Path,videoTitle,Img,Tag,introduction) " +
            "values (#{videoId},#{createTime},#{userId},#{readTimes},#{star},#{favorites},#{comment},#{path},#{videoTitle},#{img},#{tag},#{introduction})")
    public int insertVideo(Video video);

    @Update("update video set ReadTimes=ReadTimes+1 where VideoId=#{videoId}")
    public int updateVideoReadTimes(Video video);

    @Update("update video set Star=Star+#{count} where VideoId=#{video.videoId}")
    public int updateVideoStar(@Param("video")Video video, @Param("count") int count);

    @Update("update video set Favorites=Favorites+#{count} where VideoId=#{video.videoId}")
    public int updateVideoFavorites(@Param("video")Video video, @Param("count") int count);

    @Delete("delete from video where VideoId=#{videoId}")
    public int deleteVideo(String videoId);
}
