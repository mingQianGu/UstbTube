package edu.ustb.ustbtube.service;

import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SubmitService {
    public int insertVideo(Video video) {
        SqlSession session = null;
        int result = 0;
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            result = videoMapper.insertVideo(video);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    public List<Video> SelectVideoByUserId(String userId) {
        SqlSession session = null;
         List<Video> list= null;
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            list = videoMapper.getVideoByUserId(userId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return list;
    }

    public User SelectUserByUserId(String userId) {
        SqlSession session = null;
        User user =null;
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            user = videoMapper.getUserByUserId(userId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return user;
    }


    public List<Video> SelectFavoritesByUserId(String userId ){
        List<Video> list = null;
        SqlSession session = null;
        try{
            session = MybatisUtil.getSession();
            VideoMapper oiMapper = session.getMapper(VideoMapper.class);
            list = oiMapper.getFavoritesByUserId(userId);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return  list;
    }

}
