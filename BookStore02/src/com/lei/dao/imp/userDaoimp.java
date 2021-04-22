package com.lei.dao.imp;

import com.lei.beans.User;
import com.lei.dao.BasicDao;
import com.lei.dao.userDao;

public class userDaoimp extends BasicDao implements userDao {
    @Override
    public User login(String name, String password) {
        String sql = "SELECT `name`,`password` FROM `user` WHERE `name`= ? AND `password`= ? ";
        User bean = getBean(User.class, sql, name, password);
        return bean;
    }

    @Override
    public boolean register(String name) {
        String sql ="SELECT `name` FROM `user` WHERE `name`= ? ";
        User bean = getBean(User.class, sql, name);
        return bean!=null;
    }

    @Override
    public void saveinfo(String name, String password, String email) {
        String sql  = "INSERT INTO `user`(`name`,`email`,`password`) VALUES (?,?,?)";
        update(sql,name,email, password);
    }

    @Override
    public User  getUser (User user) {
        String  sql = "SELECT * FROM user WHERE name =?";
        User user1 = getBean(User.class, sql, user.getName());
        return user1;
}
}
