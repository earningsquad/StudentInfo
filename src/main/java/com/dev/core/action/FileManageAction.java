package com.dev.core.action;

import com.dev.core.model.FileInfo;
import com.dev.core.service.FileService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FileManageAction extends BasicAction {
    @Autowired
    FileService service;
    @Setter@Getter
    List<FileInfo> files;


    @Action(value = "findStufile",results = {
            @Result(name = SUCCESS,type = "json" ,params={"root", "files"})
    })
   public String findStuFile(){
        files=new ArrayList<>();
    HttpServletRequest request= ServletActionContext.getRequest();
    files=service.findFile(getUser(request).getId());
        System.out.println(files.size()+"-----");
         return SUCCESS;
    }

}
