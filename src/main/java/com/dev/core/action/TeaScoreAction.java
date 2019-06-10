package com.dev.core.action;

import com.dev.core.service.StuLessonService;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

public class TeaScoreAction extends BasicAction  {
    @Autowired
    StuLessonService service;
    @Setter @Getter
    int[] add;

    @Action(value = "TeaAddScore")
    public String addStuScore(){
        service.TeaAddLess(add);
        return ISUCCESS;
    }
}
