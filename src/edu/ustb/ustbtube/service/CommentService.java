package edu.ustb.ustbtube.service;

import edu.ustb.ustbtube.dao.CommentMapper;
import edu.ustb.ustbtube.entity.Comment;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class CommentService {


    public List<Comment> getAllComments(String videoId){
        SqlSession  session = null;
        List<Comment> commentList = null;
        String result = null;
        try{
            session = MybatisUtil.getSession();
            CommentMapper commentMapper = session.getMapper(CommentMapper.class);
            commentList = commentMapper.getCommentsByVideoId(videoId);
            for(Comment c:commentList){
                result = commentMapper.getCommentWithUserId(c.getUserId());
                c.setUserName(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return commentList;
    }

    public int addComment(Comment comment) {
        SqlSession session = null;
        int result = 0;
        try{
            session = MybatisUtil.getSession();
            CommentMapper commentMapper = session.getMapper(CommentMapper.class);
            result = commentMapper.insertComment(comment);
            session.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }
//    public String getUserNick(String id){
//        SqlSession session =null;
//        String result = null;
//        try{
//            session = MybatisUtil.getSession();
//            CommentMapper commentMapper = session.getMapper(CommentMapper.class);
//            result = commentMapper.getCommentWithUserId(id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//            return  result;
//    }

}
