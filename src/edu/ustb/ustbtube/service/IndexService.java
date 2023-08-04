package edu.ustb.ustbtube.service;

import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IndexService {

    public Video getHottest() {
        SqlSession session = null;
        Video video = new Video();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            video = videoMapper.getHottest();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return video;
    }

    //随机调用视频
    public ArrayList<Video> getGuess(int count) {
        SqlSession session = null;
        ArrayList<Video> videos = new ArrayList<>();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            videos = videoMapper.getRand(count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return videos;
    }


    //获取最新视频
    public ArrayList<Video> getNew() {
        SqlSession session = null;
        ArrayList<Video> videos = new ArrayList<>();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            videos = videoMapper.getVideoByTime(3);

            videos.get(0).setUserName(videoMapper.getUserName(videos.get(0)));
            videos.get(1).setUserName(videoMapper.getUserName(videos.get(1)));
            videos.get(2).setUserName(videoMapper.getUserName(videos.get(2)));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            videos.get(0).setCreateTime(formatter.format(formatter.parse(videos.get(0).getCreateTime())));
            videos.get(1).setCreateTime(formatter.format(formatter.parse(videos.get(1).getCreateTime())));
            videos.get(2).setCreateTime(formatter.format(formatter.parse(videos.get(2).getCreateTime())));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return videos;
    }

    public ArrayList<Video> getTopNews() {
        SqlSession session = null;
        ArrayList<Video> videos = new ArrayList<>();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            videos = videoMapper.getTopNews();

            videos.get(0).setUserName(videoMapper.getUserName(videos.get(0)));
            videos.get(1).setUserName(videoMapper.getUserName(videos.get(1)));
            videos.get(2).setUserName(videoMapper.getUserName(videos.get(2)));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            videos.get(0).setCreateTime(formatter.format(formatter.parse(videos.get(0).getCreateTime())));
            videos.get(1).setCreateTime(formatter.format(formatter.parse(videos.get(1).getCreateTime())));
            videos.get(2).setCreateTime(formatter.format(formatter.parse(videos.get(2).getCreateTime())));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return videos;
    }
}
