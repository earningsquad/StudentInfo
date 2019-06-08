package com.dev.core.action;

import com.dev.core.anno.GetUser;
import com.dev.core.model.FileInfo;
import com.dev.core.service.FileService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@Namespace("/")
@ResultPath("/")
@ParentPackage("json-default")
public class FileUploadAction extends BasicAction {
    @Autowired
    private FileService fileService;
    @Setter
    private File[] file;
    @Getter@Setter
    private String[] fileContentType;
    @Getter@Setter
    private String[] fileFileName;

    @Action(value = "filesupload",results = {
            @Result(name = SUCCESS,type = "json"),
            @Result(name = ERROR,type = "json")
    })
    public String uploadFiles(){
        HttpServletRequest request= ServletActionContext.getRequest();
        FileInfo fileInfo=new FileInfo();
        for(int i=0;i<file.length;i++){
            //得到上传文件在服务器的路径加文件名
            String target=ServletActionContext.getServletContext().getRealPath("/upload/"+fileFileName[i]);
            //获得上传的文件
            File targetFile=new File(target);
            //通过struts2提供的FileUtils类拷贝
            try {
                FileUtils.copyFile(file[i], targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            fileInfo.setFileName(fileFileName[i]);
            fileInfo.setFileLocation(target);
            fileInfo.setUploadTime(df.format(new Date()));
            fileInfo.setFileUploader(getUser(request));
            fileService.uploadFile(fileInfo);
        }
        return SUCCESS;

    }

    }



