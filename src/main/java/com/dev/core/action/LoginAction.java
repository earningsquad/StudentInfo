package com.dev.core.action;

import com.dev.core.anno.LoginRequired;
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


        try {
            System.out.println(getRequestPostData());
        } catch (IOException e) {
            e.printStackTrace();
        }
//       /* User user= JSON.parseObject(userJson,User.class);
//        if (user!=null){
//            Boolean flag=service.login(user.getUserName(),user.getPassword());
//            if (flag){
//                result.success("登录成功");
//                return SUCCESS;
//                //登录成功
//            }else {
//                //登录失败
//                result.fail("用户名或密码错误");
//                return ERROR;
//            }*/
            //logger.info("flag:"+flag+" result:"+result);
     //   }
    return ERROR;
    }



    @Action(value = "test",results = {
            @Result(name = SUCCESS,type = "json"),
            @Result(name = ERROR,type = "json")
    })
    @LoginRequired
    public String test(){
        result.success("test");
        return SUCCESS;
    }

}
