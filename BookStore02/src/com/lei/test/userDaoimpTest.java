package com.lei.test;

import com.lei.beans.User;
import com.lei.dao.imp.userDaoimp;
import com.lei.service.imp.serviceHandleImp;
import org.junit.Test;

public class userDaoimpTest {
    @Test
    public void  loginTest(){
        User lei = new userDaoimp().login("lei", "123");
        System.out.println(lei);
    }
    @Test
    public void saveTest(){
      new userDaoimp().saveinfo("lei","123","2811501159@qq.com");//2811501159@qq.com
    }
    @Test
    public void registTest(){
        boolean lei = new userDaoimp().register("lei");
        System.out.println(lei);
    }
    @Test
    public void serviceTest(){
        boolean lei = new serviceHandleImp().register("lei");
        System.out.println(lei);
    }
}
