package com.dev.core.action;

import com.alibaba.fastjson.JSON;
import com.dev.core.model.User;
import com.dev.core.service.UserService;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;


@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class LoginAction extends BasicAction{
    @Autowired
    UserService service;



    @Action(value = "login",results = {
            @Result(name = SUCCESS,type = "json"),
            @Result(name = ERROR,type = "json")
    })
    public String login()  {
          User user=null;

        try {
           String a=getRequestPostData();
            System.out.println(a);
            user= JSON.parseObject(a, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (user!=null){
            Boolean flag=service.login(user.getUserName(),user.getPassword());
            if (flag){
                result.success("登录成功");
                return SUCCESS;
                //登录成功
            }else {
                //登录失败
                result.fail("用户名或密码错误");
                return ERROR;
            }
        }
    return ERROR;
    }




}
