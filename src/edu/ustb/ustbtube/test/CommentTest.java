package edu.ustb.ustbtube.test;

import edu.ustb.ustbtube.dao.CommentMapper;
import edu.ustb.ustbtube.entity.Comment;
import edu.ustb.ustbtube.service.CommentService;
import edu.ustb.ustbtube.utils.CheckCodeUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentTest {

    private SqlSession session = null;

    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void after() throws IOException{
        session.close();
    }

    @Test
    public void testComment() {
        CommentService commentService = new CommentService();

        String av = "15";
        String comment = "好看";
        String userId = "456789";
        String code = CheckCodeUtil.getCode(6);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = formatter.format(date);

        Comment comment1 = new Comment();
        comment1.setId(code);
        comment1.setUserId(userId);
        comment1.setCreateTime(time);
        comment1.setText(comment);
        comment1.setVideoId(av);
        System.out.println(comment1);

        System.out.println(commentService.addComment(comment1));
    }
}
