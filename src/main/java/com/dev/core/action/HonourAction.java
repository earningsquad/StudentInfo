package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.*;
import com.dev.core.service.HonourDetailService;
import com.dev.core.service.HonourService;
import com.dev.core.utils.ResponseResult;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@Namespace("/errorPermissionJSON")
@ResultPath("/")
@ParentPackage("json-default")
public class HonourAction extends BasicAction{

    @Autowired
    HonourService honourService;
    @Autowired
    HonourDetailService honourDetailService;

    @Getter
    ResponseResult honourResult;

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
        honourResult = new ResponseResult();
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
        honourResult = new ResponseResult();
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
        honourResult = new ResponseResult();
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
        honourResult = new ResponseResult();
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
        honourResult = new ResponseResult();
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

    //查看申请的荣誉
    @Action(value = "showHonourDetail" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String showHonourDetail(){
        honourResult = new ResponseResult();
        /*HttpServletRequest request = ServletActionContext.getRequest();
        User user = getUser(request);*/
        List<HonourDetailFormat> honourDetailList = honourDetailService.showHonourDetail();
        honourResult.success(honourDetailList,honourDetailList.size());
        return SUCCESS;
    }

    //申请荣誉
    @Action(value = "updateHonourDetail" , results = {
            @Result(name = SUCCESS , type = "json"),
            @Result(name = ERROR , type = "json")
    })
    public String updateHonourDetail(){
        honourResult = new ResponseResult();
        HonourDetail honourDetail = null;
        try {
            honourDetail = JSONObject.parseObject(getRequestPostData(),HonourDetail.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        honourDetailService.updateHonourDetail(honourDetail);
        honourResult.success();
        return SUCCESS;
    }

}
