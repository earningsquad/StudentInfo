package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.model.*;
import com.dev.core.service.HonourDetailService;
import com.dev.core.service.HonourService;
import com.dev.core.utils.HonourResult;
import com.opensymphony.xwork2.ActionContext;
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
    HonourResult honourResult ;

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
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String findHonour(){
        honourResult = new HonourResult();
        Honour honour = new Honour(getType(),getName(),getDetail());
        List<Honour> honourList = new ArrayList<>();
        honourList = honourService.findHonourBy(honour);
        honourResult.success(honourList,honourList.size());
        return SUCCESS;
    }

    //新增荣誉
    @Action(value = "addHonour" , results = {
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String addHonour(){
        honourResult = new HonourResult();
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
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String deleteHonour(){
        honourResult = new HonourResult();
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
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String addHonourDetail(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        honourResult = new HonourResult();
        HonourDetail honourDetail = null;
        try {
            honourDetail = JSONObject.parseObject(getRequestPostData(),HonourDetail.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        honourDetail.setStudentInfo(me.getStudentInfo());
        honourDetailService.addHonourDetail(honourDetail);
        honourResult.success();
        return SUCCESS;
    }

    //查看我申请的荣誉
    @Action(value = "showSelfHonourDetail" , results = {
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String showSelfHonourDetail(){
        User me= (User) ActionContext.getContext().getSession().get("user");
        honourResult = new HonourResult();
        /*HttpServletRequest request = ServletActionContext.getRequest();
        User user = getUser(request);*/
        HonourDetail honourDetail = new HonourDetail();
        honourDetail.setStudentInfo(me.getStudentInfo());
        List<HonourDetailFormat> honourDetailList = honourDetailService.showSelfHonourDetail(honourDetail);
        honourResult.success(honourDetailList,honourDetailList.size());
        return SUCCESS;
    }

    //查看我申请的荣誉
    @Action(value = "showHonour" , results = {
            @Result(name = SUCCESS , type = "json" , params={"root", "honourResult"}),
            @Result(name = ERROR , type = "json")
    })
    public String showHonour(){
        honourResult = new HonourResult();
        /*HttpServletRequest request = ServletActionContext.getRequest();
        User user = getUser(request);*/

        List<Honour> honourDetailList = honourService.findHonour();
        honourResult.success(honourDetailList,honourDetailList.size());
        return SUCCESS;
    }

}
