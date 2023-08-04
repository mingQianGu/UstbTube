package edu.ustb.ustbtube.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try{
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() throws SQLException{
        return sqlSessionFactory.openSession();
    }

}
