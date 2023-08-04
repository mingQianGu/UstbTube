package edu.ustb.ustbtube.service;

import edu.ustb.ustbtube.dao.SearchMapper;
import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SearchService {
        public List<Video> searchVideoByName(String title){
            SqlSession session =null;
            List<Video> videoList = null;
            DetailService detailService = new DetailService();
            try{
                session = MybatisUtil.getSession();
                SearchMapper searchMapper = session.getMapper(SearchMapper.class);
                videoList = searchMapper.searchByTitle(title);
                VideoMapper videoMapper = session.getMapper(VideoMapper.class);

                for(Video video :videoList){
                    video.setUserName(videoMapper.getUserNameByUserId(video.getUserId()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                session.close();
            }
            return videoList;
        }
}
