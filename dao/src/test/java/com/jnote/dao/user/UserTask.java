package com.jnote.dao.user;

import com.jnote.dao.UserMapper;
import com.jnote.model.User;

public class UserTask implements Runnable {

    private UserMapper mapper;

    private int index;


    public UserTask(UserMapper mapper, int index) {
        this.mapper = mapper;
        this.index = index;
    }

    public void insert(int i, UserMapper mapper) {
        User user = getUser(i);
        mapper.insert(user);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private User getUser(int i) {
        User user = new User();
        user.setAddress("Adress" + i);
        user.setAge(i);
        user.setName("Wangjiabao" + i);
        user.setPhone("1324009812" + i % 10);
        user.setScore((double) i);
        user.setSex((byte) (i % 2 == 0 ? 1 : 2));
        return user;
    }

    @Override
    public void run() {
        insert(index, mapper);
    }
}
