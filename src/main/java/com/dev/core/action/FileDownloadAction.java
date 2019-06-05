package com.dev.core.action;

import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.stereotype.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Controller
@Namespace("/")
@ResultPath("/")
public class FileDownloadAction extends BasicAction {

    @Getter @Setter
    private InputStream attachstream ;  // 文件输入流
    @Getter@Setter
    private String attachname ;//文件名
    @Getter@Setter
    private String fileName;//文件路径

    @Action(value = "/toDownload",
            results = {@Result(name = "download", type = "stream",
                    params = {
                            "contentType", "application/octet-stream",
                            "inputName", "attachstream",
                            "contentDisposition", "attachment;filename=\"${attachname}\"",
                            "bufferSize", "4096"
                    })})
    public String toDownload(){
        String path = ServletActionContext.getServletContext().getRealPath("/upload/" + fileName);//fileName是页面传过来的参数
        System.out.println(path+"-----------------");
        try {
            attachstream = new FileInputStream(path);
            String []attachnames = fileName.split("/");
            attachname = attachnames[attachnames.length - 1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "download";
    }

}
