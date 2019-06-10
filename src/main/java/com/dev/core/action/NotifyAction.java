package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.Notify;
import com.dev.core.model.NotifyFormat;
import com.dev.core.model.User;
import com.dev.core.service.NotifyService;
import com.dev.core.service.UserService;
import com.dev.core.utils.JsonResult;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class NotifyAction extends BasicAction{

    @Autowired
    NotifyService notifyService;
    @Autowired
    UserService userService;

    @Getter@Setter
    ArrayList<Integer> add;

    @Getter
    ResponseResult results;

    //查询通告
    @Action(value = "findNotify" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findNotify(){
        results = new ResponseResult();
        List<NotifyFormat> notifyList = notifyService.find();
        results.success(notifyList,notifyList.size());
        return SUCCESS;
    }

    //查询被通知的学生
    @Action(value = "getStudentUser" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String getStudentUser(){
        results = new ResponseResult();
        List<User> userList = userService.getStudentUser();
        results.success(userList,userList.size());
        return SUCCESS;
    }

    @Action(value = "postNotify" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String postNotify(){
        Notify notify = null;
        try {
            notify = JSONObject.parseObject(getRequestPostData(),Notify.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        notify.setCreateTime(new Date());
        results = new ResponseResult();
        notifyService.postNotify(notify);
        results.success();
        return SUCCESS;
    }

    @Action(value = "deleteNotify" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String deleteNotify() throws IOException {
        if(add.size() == 0){
            return SUCCESS;
        }
        notifyService.deleteNotify(add);
        results = new ResponseResult();
        results.success();
        return SUCCESS;
    }

}
