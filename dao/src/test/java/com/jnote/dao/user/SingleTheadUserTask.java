package com.jnote.dao.user;

import com.jnote.dao.UserMapper;

public class SingleTheadUserTask {

    private UserMapper mapper;

    private int taskTimes;

    public SingleTheadUserTask(UserMapper mapper, int taskTimes) {
        this.mapper = mapper;
        this.taskTimes = taskTimes;
    }

    public void starkWork(){
        for(int i =0;i<taskTimes;i++){
            new UserTask(mapper,i).run();
        }
    }
}
