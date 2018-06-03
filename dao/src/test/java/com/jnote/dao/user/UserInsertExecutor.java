package com.jnote.dao.user;

import com.jnote.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.concurrent.ExecutorService;

public class UserInsertExecutor {

    private  int times;

    private ExecutorService service;

    private SqlSession sqlSession;

    public UserInsertExecutor(int times, ExecutorService service, SqlSession sqlSession) {
        this.times = times<=0?1:times;
        this.service = service;
        this.sqlSession = sqlSession;
    }

    public void execute(){
        for(int i=0;i<times;i++){
            service.execute(new UserTask(sqlSession.getMapper(UserMapper.class),i));
        }
        service.shutdown();
        while(true){
            if(service.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }
        sqlSession.commit();
    }
}
