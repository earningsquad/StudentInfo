package com.dev.core.service;

import com.dev.core.dao.UserDao;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    public Boolean login(String userName,String passwd){
        User user=new User();
        user.setUserName(userName);
        user.setPassword(passwd);
        Example<User> example=Example.of(user);
        Optional opt=dao.findOne(example);
        if (opt.isPresent())
            return true;
        return false;
    }
}
