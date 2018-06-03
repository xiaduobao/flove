package com.jnote.dao.user;

import com.jnote.dao.UserMapper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadUserTask {

    private UserMapper mapper;

    private int taskTimes;

    private ExecutorService service;

    public MultiThreadUserTask(UserMapper mapper, int taskTimes, int threadCount) {
        this.mapper = mapper;
        this.taskTimes = taskTimes > 0? taskTimes : 1;
        service = Executors.newFixedThreadPool( threadCount <= 0 ? 10 : threadCount);
    }

    public void startWork() {
        System.out.println("start");
        for (int i = 0; i < taskTimes; i++) {
            service.execute(new UserTask(mapper, i));
        }

        service.shutdown();
        while(true){
            if(service.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
        }
    }

}
