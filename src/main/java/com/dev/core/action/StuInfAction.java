package com.dev.core.action;

import com.alibaba.fastjson.JSON;
import com.dev.core.model.StudentInfo;
import com.dev.core.model.User;
import com.dev.core.service.StuInfService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class StuInfAction extends BasicAction {
    @Autowired
    StuInfService service;
    @Setter@Getter
    List<StudentInfo> students;
    @Getter@Setter
    int stuId;
    @Getter@Setter
    String information;
    @Getter@Setter
    StudentInfo studentInfo;


    @Action(value = "findStu",results = {
            @Result(name = SUCCESS,type = "json" )
    })
     public String findStu(){
         students=service.getStudentsInfo();
         return SUCCESS;
     }

    @Action(value = "deleteStu",results = {
            @Result(name = SUCCESS,type = "json" )
    })
   public String deleteStu(){
        service.deleteStu(stuId);
        return SUCCESS;
   }

    @Action(value = "addStu",results = {
            @Result(name = SUCCESS,type = "json" )
    })
    public String addStu(){
        if(stuId==0){
        StudentInfo stu=new StudentInfo();
         stu=JSON.parseObject(information,StudentInfo.class);
         User u=new User();
        u=JSON.parseObject(information,User.class);
        stu.setUser(u);
        service.addStu(stu);

        }else{
            StudentInfo stu=new StudentInfo();
            stu=JSON.parseObject(information,StudentInfo.class);
            User u=new User();
            u=JSON.parseObject(information,User.class);
            stu.setUser(u);
            System.out.println(u.getId()+"----"+information);
            stu.setId(stuId);
         service.updateStu(stu);
        }
        return  SUCCESS;

    }

    @Action(value = "findTheStu",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "studentInfo"}),
            @Result(name = ERROR,type = "json" )
    })
public String findTheStu(){

        studentInfo=service.findtheStu(stuId);
        System.out.println(studentInfo.getName()+"----");
        return SUCCESS;
}


}