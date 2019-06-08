package com.dev.core.action;

import com.alibaba.fastjson.JSON;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.*;
import com.dev.core.service.MyInfoService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Scope("prototype")
@Namespace("/stu")
public class MyInfoAction extends BasicAction{
    @Autowired
    MyInfoService myInfoService;
    @Action("myBasicInfo")
    @LoginRequired
    @RoleRequired("stu")
    public String getMyBasicInfo(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        try {
            String myJson=getRequestPostData();
            if (myJson!=null&&myJson.length()>0){
                MyBasicInfo myBasicInfo= JSON.parseObject(myJson,MyBasicInfo.class);
                myInfoService.update(myBasicInfo);
            }
            result.success(myInfoService.getBasic(me.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ISUCCESS;
    }

    @Action("myWholeInfo")
    @LoginRequired
    @RoleRequired("stu")
    public String getMyWholeInfo(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        StudentInfo myWholeInfo=myInfoService.getWholeInfo(me.getId());
        List<StuLesson> myScores=myInfoService.getMyScores(myWholeInfo.getId());
        List<HonourDetail> myHonour=myInfoService.getMyHonour(myWholeInfo.getId());
        Map myInfos=new HashMap<>();
        myInfos.put("myWholeInfo",myWholeInfo);
        myInfos.put("myScores",myScores);
        myInfos.put("myHonour",myHonour);
        result.success(myInfos);
        return ISUCCESS;
    }
}
