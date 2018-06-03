package com.jnote.dao.user;

import com.jnote.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;

public class UserTest {

    @Test
    public void dao() throws IOException, InterruptedException {
        SqlSession sqlSession = SqlSessionManager.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        TableCleaner.truncateTable();
        int times = 10000;
        SingleTheadUserTask singleTheadUserTask = new SingleTheadUserTask(mapper, times);
        MultiThreadUserTask multiThreadUserTask = new MultiThreadUserTask(mapper,times,5);
        StopWatch watch = new StopWatch("watch");
        watch.start("insert user by single thread");

        singleTheadUserTask.starkWork();
        sqlSession.commit();
        watch.stop();

        TableCleaner.truncateTable();

        watch.start("insert user by multi thread");
        multiThreadUserTask.startWork();
        sqlSession.commit();
        watch.stop();
        System.out.println(watch.prettyPrint());
    }



    @Test
    public void truncateTable() throws IOException {
        SqlSession sqlSession = SqlSessionManager.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.truncateTable("user");
        sqlSession.commit();
    }
}
