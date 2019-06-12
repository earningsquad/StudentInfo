package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.anno.JsonObj;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RawPostData;
import com.dev.core.model.User;
import com.dev.core.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class    LoginAction extends BasicAction{
    @Autowired
    UserService service;

    @Action(value = "login")
    public String login(@JsonObj User user  )  {

          //  User user= JSON.parseObject(rawData,User.class);
            if (service.login(user)){
                setUser(user);
                result.success(user);
            }
            else {
                result.fail("失败");
            }

        return ISUCCESS;
    }



    @Action(value = "test")
    public String test(@GetUser User user,@JsonObj User string){
        result.success("test");
        System.out.println(user+string.getUserName());
        return ISUCCESS;
    }

    @LoginRequired
    @Action(value = "test2")
    public String test1(@RawPostData String string){
        result.success("test");
        return ISUCCESS;
    }

}
