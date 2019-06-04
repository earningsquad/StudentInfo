package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

   @Autowired
   @Qualifier("baseDao")
   IBaseDao<User> dao;

    public Boolean login(String userName,String passwd){
        //User user=new User();
        //user.setUserName(userName);
       // user.setPassword(passwd);
       // Example<User> example1=Example.of(user);
        //Optional<User> opt=dao.findOne(example1);
        Map params=new HashMap();
        params.put("userName",userName);
        params.put("password",passwd);
        Object object=dao.find("from User  u where userName=? and password=?",params);
        if (object!=null)
            return true;
        return false;
    }
}
