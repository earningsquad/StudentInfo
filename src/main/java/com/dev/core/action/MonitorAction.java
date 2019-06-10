package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.model.Monitor;
import com.dev.core.model.TeaInvigilate;
import com.dev.core.service.MonitorService;

import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class MonitorAction extends BasicAction {
    @Autowired
    MonitorService service;
    @Setter@Getter
    List<TeaInvigilate> tis;

    @Action(value = "AllMonitor",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "tis"}),
    })
    public String AllMonitors(){
        List<Monitor> monitors=service.allMonitor();
        tis=new ArrayList<>();
       for(Monitor m: monitors){
            tis.add(new TeaInvigilate(m));
       }
        return SUCCESS;
    }


    @Action(value = "beTeacher",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "tis"}),
    })
    public String beTeacher(){
     List<Monitor> monitors=service.myTeacher(getUser(ServletActionContext.getRequest()).getId());
     tis=new ArrayList<>();
     for(Monitor m: monitors){
         tis.add(new TeaInvigilate(m));
     }
     return SUCCESS;


    }
    @Action(value = "beInvigilate",results = {
            @Result(name = SUCCESS,type = "json" , params={"root", "tis"}),
    })
    public String beInvigilate(){

        List<Monitor> monitors=service.myInvigilate(getUser(ServletActionContext.getRequest()).getId());
        //List<Monitor> monitors=service.myInvigilate(2);
        tis=new ArrayList<>();
        for(Monitor m: monitors){
            tis.add(new TeaInvigilate(m));
        }
        return SUCCESS;


    }




}
