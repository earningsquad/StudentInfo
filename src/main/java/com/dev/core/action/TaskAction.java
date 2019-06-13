package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.anno.JsonObj;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.Task;
import com.dev.core.model.TeacherInfo;
import com.dev.core.model.User;
import com.dev.core.service.TaskService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TaskAction extends BasicAction{
    @Autowired
    TaskService service;


    @LoginRequired
    @RoleRequired("tea")
    @Action("getTaskInfo")
    public String getTaskInfo(@GetUser User user){
        TeacherInfo teacherInfo=null;
        List list=service.getTeacherInfo(user.getId());
        if (list!=null&&list.size()>0){
            teacherInfo= (TeacherInfo) list.get(0);
            Map map=new HashMap<>();
            map.put("tasks",service.getTaskInfo(teacherInfo.getId()));

            map.put("teacher",teacherInfo);
            result.success(map);
        }else {
            result.fail("教师未找到");
        }

        return ISUCCESS;
    }


    @LoginRequired
    @RoleRequired("tea")
    @Action("saveTaskInfo")
    public String saveTaskInfo(@GetUser User user, @JsonObj Task task){
      try {
         List list= service.getTeacherInfo(user.getId());
         TeacherInfo teacherInfo=null;
         if (list!=null&&list.size()>0)
             teacherInfo= (TeacherInfo) list.get(0);
         task.setTeacherInfo(teacherInfo);
         service.saveTask(task);
          result.success("成功");
      }catch (Exception e){
          result.fail(e.getMessage());
      }
        return ISUCCESS;
    }


}
