package com.dev.core.action;

import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.StudentInfo;
import com.dev.core.model.User;
import com.dev.core.service.TimetableService;
import com.dev.core.service.UserService;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class TimetableAction extends BasicAction{

    @Autowired
    TimetableService timetableService;

    @Autowired
    UserService userService;

    @Getter
    ResponseResult results;

    //查询通告
    @Action(value = "getLessonBySid" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    @LoginRequired
    @RoleRequired("stu")
    public String getLessonBySid(){
        results = new ResponseResult();
        User user=getUser(ServletActionContext.getRequest());
        StudentInfo studentInfo=userService.getStudentByUid(user.getId());
        int sid=studentInfo.getId();
        List<List<String>> resultList = timetableService.getTimetableBySid(sid);
        results.success(resultList,resultList.size());
        return SUCCESS;
    }

}
