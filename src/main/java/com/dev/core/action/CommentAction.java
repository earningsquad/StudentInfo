package com.dev.core.action;


import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.Comment;
import com.dev.core.service.CommentService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
    @Getter@Setter
    String commentJson;

    //添加留言
    @Action(value = "postComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String postComment(){
        Comment comment = JSONObject.parseObject(commentJson,Comment.class);
        commentService.postComment(comment);
        return SUCCESS;
    }

    //删除留言
    @Action(value = "deleteComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String deleteComment(){
        Comment comment = JSONObject.parseObject(commentJson,Comment.class);
        commentService.deleteComment(comment);
        return SUCCESS;
    }

    @Action(value = "findSelfComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findSelfComment(){
        Comment comment = JSONObject.parseObject(commentJson,Comment.class);
        List<Comment> selfComment= commentService.findSelfComment(comment);
        result.setBean(selfComment);
        return SUCCESS;
    }

    @Action(value = "findComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findComment(){
        Comment comment = JSONObject.parseObject(commentJson,Comment.class);
        Map<Comment,List<Comment>> comments= commentService.findComment(comment);
        result.setBean(comments);
        return SUCCESS;
    }

    //回复留言
    @Action(value = "answerComment" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String answerComment(){
        Comment comment = JSONObject.parseObject(commentJson,Comment.class);
        commentService.answerComment(comment);
        return SUCCESS;
    }


}
