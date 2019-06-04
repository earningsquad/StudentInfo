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
        Example<User> example1=Example.of(user);
        Optional<User> opt=dao.findOne(example1);
        if (opt.isPresent())
            return true;
        return false;
    }
}
