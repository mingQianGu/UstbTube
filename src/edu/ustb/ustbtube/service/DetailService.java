package edu.ustb.ustbtube.service;

import edu.ustb.ustbtube.dao.FavoritesMapper;
import edu.ustb.ustbtube.dao.StarMapper;
import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.Favorites;
import edu.ustb.ustbtube.entity.Star;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DetailService {

    public Video getVideoById(String id) {
        SqlSession session = null;
        Video video = new Video();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            video = videoMapper.getVideoById(id);
            video.setUserName(videoMapper.getUserName(video));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return video;
    }

    public int updateVideoOne(Video video) {
        SqlSession session = null;
        int result = 0;
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            result = videoMapper.updateVideoReadTimes(video);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return result;
    }

    public ArrayList<Video> getAbout(Video video) {
        SqlSession session = null;
        ArrayList<Video> videos = new ArrayList<>();
        try {
            session = MybatisUtil.getSession();
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            videos = videoMapper.getRandByTag(video, 3);
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

    public int getUserAbout(String av, String userId) {
        SqlSession session = null;
        int result = 0;
        try {
            session = MybatisUtil.getSession();
            StarMapper starMapper = session.getMapper(StarMapper.class);
            FavoritesMapper favoritesMapper = session.getMapper(FavoritesMapper.class);

            Star star = new Star();
            star.setVideoId(av);
            star.setUserId(userId);
            result += starMapper.selectIsStar(star) * 100;

            Favorites favorites = new Favorites();
            favorites.setVideoId(av);
            favorites.setUserId(userId);
            result += favoritesMapper.selectIsFavorites(favorites) * 10;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    public int changeStar(String av, String userId) {
        SqlSession session = null;
        int result = -1;
        try {
            session = MybatisUtil.getSession();
            StarMapper starMapper = session.getMapper(StarMapper.class);
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            Star star = new Star();
            star.setVideoId(av);
            star.setUserId(userId);
            star.setId("1");
            if(starMapper.selectIsStar(star) == 1) {
                int i = starMapper.deleteStar(star);
                i = videoMapper.updateVideoStar(videoMapper.getVideoById(av), -1);
                result = 0;
            } else {
                int i = videoMapper.updateVideoStar(videoMapper.getVideoById(av), 1);
                result = starMapper.insertStar(star);
            }

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    public int changeFavorites(String av, String userId) {
        SqlSession session = null;
        int result = -1;
        try {
            session = MybatisUtil.getSession();
            FavoritesMapper favoritesMapper = session.getMapper(FavoritesMapper.class);
            VideoMapper videoMapper = session.getMapper(VideoMapper.class);

            Favorites favorites = new Favorites();
            favorites.setVideoId(av);
            favorites.setUserId(userId);
            favorites.setId("1");
            if(favoritesMapper.selectIsFavorites(favorites) == 1) {
                int i = favoritesMapper.deleteFavorites(favorites);
                i = videoMapper.updateVideoFavorites(videoMapper.getVideoById(av), -1);
                result = 0;
            } else {
                int i = videoMapper.updateVideoFavorites(videoMapper.getVideoById(av), 1);
                result = favoritesMapper.insertFavorites(favorites);
            }

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

}
