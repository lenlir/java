package com.lei.service.imp;

import com.lei.beans.User;
import com.lei.dao.imp.userDaoimp;
import com.lei.dao.userDao;
import com.lei.service.serviceHandle;

public class serviceHandleImp  implements serviceHandle {
    userDao userDao = new userDaoimp();
    @Override
    public User login(String name, String password) {
        User login = userDao.login(name, password);
        return login;
    }

    @Override
    public boolean register(String name) {
        boolean register = userDao.register(name);
        return register;
    }

    @Override
    public void saveinfo(String name, String password, String email) {
        userDao.saveinfo(name,password,email);
    }

    @Override
    public User getUser(User user) {
        return new userDaoimp().getUser(user) ;
    }
}
