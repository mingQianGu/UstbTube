package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Select("SELECT * FROM comment WHERE videoid = #{videoId}")
    public List<Comment> getCommentsByVideoId(@Param("videoId") String videoId);

    @Insert("INSERT INTO comment (Id, VideoId, UserId, Text, CreateTime) VALUES (#{id} ,#{videoId}, #{userId}, #{text}, #{createTime})")
    public int insertComment(Comment comment);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    public int deleteComment(@Param("id") String id);

    @Select("select nickname from user where userid=#{id} ")
    public String getCommentWithUserId(@Param("id") String  id);

}
