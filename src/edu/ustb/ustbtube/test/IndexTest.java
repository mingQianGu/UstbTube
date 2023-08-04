package edu.ustb.ustbtube.test;

import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.DetailService;
import edu.ustb.ustbtube.service.IndexService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class IndexTest {

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
    public void testGetVideoById() {

        VideoMapper videoMapper = session.getMapper(VideoMapper.class);
        IndexService indexService = new IndexService();

        Video video = indexService.getHottest();
        System.out.println(video);

        System.out.println(video.getPath());

    }
}
