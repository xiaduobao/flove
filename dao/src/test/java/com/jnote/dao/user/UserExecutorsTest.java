package com.jnote.dao.user;

import com.jnote.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserExecutorsTest {

    @Test
    public void ExecutorsDiffTest(){

        int threadCount = 5;
        int taskTimes = 1000;

        ExecutorService service1 = Executors.newFixedThreadPool(threadCount);

        ExecutorService service2 = Executors.newCachedThreadPool();

        ExecutorService service3 = Executors.newSingleThreadExecutor();

        ExecutorService service4 = Executors.newScheduledThreadPool(threadCount);

        SqlSession sqlSession = SqlSessionManager.getSqlSession();

        UserInsertExecutor executor1 = new UserInsertExecutor(taskTimes , service1 , sqlSession);

        UserInsertExecutor executor2 = new UserInsertExecutor(taskTimes , service2 , sqlSession);
        UserInsertExecutor executor3 = new UserInsertExecutor(taskTimes , service3 , sqlSession);
        UserInsertExecutor executor4 = new UserInsertExecutor(taskTimes , service4 , sqlSession);

        StopWatch watch = new StopWatch("ThreadPoolTest");

        TableCleaner.truncateTable();
        watch.start("newFixedThreadPool");
        executor1.execute();
        watch.stop();

        TableCleaner.truncateTable();
        watch.start("newCachedThreadPool");
        executor2.execute();
        watch.stop();

        TableCleaner.truncateTable();
        watch.start("newSingleThreadExecutor");
        executor3.execute();
        watch.stop();

        TableCleaner.truncateTable();
        watch.start("newScheduledThreadPool");
        executor4.execute();
        watch.stop();

        System.out.println(watch.prettyPrint());

    }

}
