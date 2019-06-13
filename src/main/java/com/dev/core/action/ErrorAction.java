package com.dev.core.action;

import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
@Namespace("/errorPermissionJSON")
@ResultPath("/")
@ParentPackage("json-default")
public class ErrorAction extends BasicAction{

    @Action(value = "noLogin",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "result", "ignoreHierarchy", "false"})
    })
    public String noLogin(){
        result.fail("没有登录");
        return SUCCESS;
    }
    @Action(value = "noPermission",results = {
            @Result(name = SUCCESS,type = "json", params={"root", "result", "ignoreHierarchy", "false"})
    })
    public String noPermission(){
        result.setBean("");
        result.fail("没有权限");
        return SUCCESS;
    }


}
