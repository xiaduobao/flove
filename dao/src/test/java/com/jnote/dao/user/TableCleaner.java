package com.jnote.dao.user;

import com.jnote.dao.UserMapper;
import com.mysql.jdbc.StringUtils;

public class TableCleaner {

    public static void truncateTable(String table) {
        if (StringUtils.isNullOrEmpty(table)) {
            table = "user";
        }
        //TODO 
        SqlSessionManager.getSqlSession().getMapper(UserMapper.class).truncateTable(table);
        SqlSessionManager.getSqlSession().commit();
    }

    public static void truncateTable() {
        truncateTable("");
    }
}
