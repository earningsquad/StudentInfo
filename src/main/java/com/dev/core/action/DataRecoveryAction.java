package com.dev.core.action;

import com.dev.core.service.FileService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class DataRecoveryAction extends BasicAction {
    @Autowired
    private FileService fileService;
    @Getter@Setter
    private String sqlName;
    @Action(value = "dataRecovery",results = {
            @Result(name = SUCCESS,type = "json"),
            @Result(name = ERROR,type = "json")
    })
    public String recoveryData(){
     String path=  ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/upload")+"/";
        fileService.recoveryData(path+sqlName);
         return SUCCESS;
    }
    @Action(value = "dataBackup")
    public String BackupData(){
        fileService.backup();
        return ISUCCESS;
    }
}
