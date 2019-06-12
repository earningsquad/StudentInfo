package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

   @Autowired
   @Qualifier("baseDao")
   IBaseDao<User> dao;

    public Boolean login(User user){
        //User user=new User();
        //user.setUserName(userName);
       // user.setPassword(passwd);
       // Example<User> example1=Example.of(user);
        //Optional<User> opt=dao.findOne(example1);
        Map params=new HashMap();
        params.put("userName",user.getUserName());
        params.put("password",user.getPassword());
        List list=dao.find("from User where userName=:userName and password=:password",params);

        if (list!=null&&list.size()>0){
            String role=((User)list.get(0)).getRole();
            int uid= ((User)list.get(0)).getId();
            user.setRole(role);
            user.setId(uid);
            user.setStudentInfo(((User)list.get(0)).getStudentInfo());
            user.setTeacherInfo(((User)list.get(0)).getTeacherInfo());
            return true;
        }

        return false;
    }
}
