package com.dev.core.action;


import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.Comment;
import com.dev.core.service.CommentService;
import com.dev.core.utils.JsonResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class CommentAction extends BasicAction{

    @Autowired
    CommentService commentService;

    @Getter
    JsonResult result;

    //添加留言
    @Action(value = "postComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String postComment(){
        Comment comment = null;
        try {
            comment = JSONObject.parseObject(getRequestPostData(),Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        commentService.postComment(comment);
        return SUCCESS;
    }

    //删除留言
    @Action(value = "deleteComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String deleteComment(){
        Comment comment = null;
        try {
            comment = JSONObject.parseObject(getRequestPostData(),Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!commentService.deleteComment(comment)){
            result = JsonResult.failX("不存在该条数据");
            return ERROR;
        }
        return SUCCESS;
    }

    @Action(value = "findSelfComment" , results = {
            @Result(name = "SUCCESS" , type = "json"),
            @Result(name = "ERROR" , type = "json")
    })
    public String findSelfComment(){
        Comment comment = null;
        try {
            comment = JSONObject.parseObject(getRequestPostData(),Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Comment> selfComment= commentService.findSelfComment(comment);
        result = JsonResult.successX(selfComment);
        return "SUCCESS";
    }

    @Action(value = "findComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findComment(){
        Comment comment = null;
        try {
            comment = JSONObject.parseObject(getRequestPostData(),Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Comment,List<Comment>> comments= commentService.findComment(comment);
        result = JsonResult.successX(comments);
        return SUCCESS;
    }

    //回复留言
    @Action(value = "answerComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String answerComment(){
        Comment comment = null;
        try {
            comment = JSONObject.parseObject(getRequestPostData(),Comment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        commentService.answerComment(comment);
        return SUCCESS;
    }


}
