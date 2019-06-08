package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.*;
import com.dev.core.service.HonourDetailService;
import com.dev.core.service.HonourService;
import com.dev.core.utils.HonourResult;
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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/")
@ResultPath("/")
public class HonourAction extends BasicAction{

    @Autowired
    HonourService honourService;
    @Autowired
    HonourDetailService honourDetailService;

    @Getter
    HonourResult honourResult = new HonourResult();

    @Getter@Setter
    private String name;
    @Getter@Setter
    private String detail;
    @Getter@Setter
    private int type;
    @Getter@Setter
    private int stuId;

    //查询荣誉
    @Action(value = "findHonour" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String findHonour(){
        Honour honour = new Honour(getType(),getName(),getDetail());
        List<Honour> honourList = new ArrayList<>();
        honourList = honourService.findHonourBy(honour,1);
        honourResult.success(honourList,honourList.size());
        return SUCCESS;
    }

    //新增荣誉
    @Action(value = "addHonour" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String addHonour(){
        Honour honour = null;
        try {
            honour = JSONObject.parseObject(getRequestPostData(),Honour.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        honourService.addHonour(honour);
        honourResult.success();
        return SUCCESS;
    }

    //删除荣誉
    @Action(value = "deleteHonour" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String deleteHonour(){
        Honour honour = null;
        try {
            honour = JSONObject.parseObject(getRequestPostData(),Honour.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        honourService.deleteHonur(honour);
        honourResult.success();
        return SUCCESS;
    }

    //申请荣誉
    @Action(value = "addHonourDetail" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String addHonourDetail(){
        HonourDetail honourDetail = null;
        try {
            honourDetail = JSONObject.parseObject(getRequestPostData(),HonourDetail.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        honourDetailService.addHonourDetail(honourDetail);
        honourResult.success();
        return SUCCESS;
    }

    //查看我申请的荣誉
    @Action(value = "showSelfHonourDetail" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String showSelfHonourDetail(){
        /*HttpServletRequest request = ServletActionContext.getRequest();
        User user = getUser(request);*/
        HonourDetail honourDetail = new HonourDetail();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId(getStuId());
        honourDetail.setStudentInfo(studentInfo);
        List<HonourDetailFormat> honourDetailList = honourDetailService.showSelfHonourDetail(honourDetail);
        honourResult.success(honourDetailList,honourDetailList.size());
        return SUCCESS;
    }

}
