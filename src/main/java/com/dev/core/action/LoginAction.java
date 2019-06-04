package com.dev.core.action;

import com.alibaba.fastjson.JSON;
import com.dev.core.model.User;
import com.dev.core.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class LoginAction extends BasicAction{
    @Autowired
    UserService service;
    @Getter@Setter
    String userJson;


    @Action(value = "login",results = {
            @Result(name = SUCCESS,type = "json"),
            @Result(name = ERROR,type = "json")
    })
    public String login(){
        User user= JSON.parseObject(userJson,User.class);
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
            //logger.info("flag:"+flag+" result:"+result);
        }
    return ERROR;
    }




}
