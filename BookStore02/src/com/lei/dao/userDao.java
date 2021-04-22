package com.lei.dao;

import com.lei.beans.User;

public interface userDao {
       User login (String name , String password);

    /**
     *
     * @param name
     * @return true 为已经注册过的 false 为 未注册
     */
       boolean register(String name);
      void saveinfo(String name ,String password ,String email);
       User  getUser(User user);



}
