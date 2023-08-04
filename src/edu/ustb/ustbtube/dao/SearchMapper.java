package edu.ustb.ustbtube.dao;

import edu.ustb.ustbtube.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SearchMapper {
    @Select("select * from video where VideoTitle like concat('%',#{title},'%') ")
    public List<Video> searchByTitle(@Param("title") String title);
}
