package com.dev.core.action;

import com.alibaba.fastjson.JSON;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.User;
import com.dev.core.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class    LoginAction extends BasicAction{
    @Autowired
    UserService service;


    @Action(value = "login")
    public String login()  {
        try {
            User user= JSON.parseObject(getRequestPostData(),User.class);
            if (service.login(user)){
                setUser(user);
                result.success(user);
            }
            else {
                result.fail("失败");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return ISUCCESS;
    }



    @Action(value = "test")
    @LoginRequired
    @RoleRequired("tea")
    public String test(){
        result.success("test");
        return ISUCCESS;
    }

    @LoginRequired
    public String test1(){
        result.success("test");
        return ISUCCESS;
    }

}
