package com.lei.test;


import com.lei.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

public class connectionTest {
    @Test
    public void test(){
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
