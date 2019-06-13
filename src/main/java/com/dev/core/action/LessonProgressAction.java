package com.dev.core.action;

import com.alibaba.fastjson.JSONObject;
import com.dev.core.anno.GetUser;
import com.dev.core.anno.LoginRequired;
import com.dev.core.anno.RawPostData;
import com.dev.core.anno.RoleRequired;
import com.dev.core.model.Lesson;
import com.dev.core.model.TeacherInfo;
import com.dev.core.model.User;
import com.dev.core.service.LessonProgressService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Calendar;
import java.util.List;

@Controller
public class LessonProgressAction extends BasicAction{


    @Autowired
    LessonProgressService lessonProgressService;

   @LoginRequired
   @RoleRequired("tea")
   @Action("myLessonProgress")
    public String getMyLessonProgress(@GetUser User user, @RawPostData String data){
        TeacherInfo teacherInfo=lessonProgressService.getTeacherInfo(user.getId());
        Lesson lesson=lessonProgressService.getMyLesson(teacherInfo.getId());

        if (data!=null&&!data.equals("")){
            JSONObject jsonObject=JSONObject.parseObject(data);
            String progress=jsonObject.getString("progress");
            String words=jsonObject.getString("words");
            Calendar cal=Calendar.getInstance();
            String date= cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
            lesson.setProgress(progress+":"+words+":"+date);
            try {
                lessonProgressService.updateMyProgress(lesson);
            }catch (Exception e){
                result.fail(e.getMessage());
                return IERROR;
            }

        }

        result.success(lesson);
    return ISUCCESS;
   }



    @LoginRequired
    @RoleRequired("admin")
    @Action("allProgress")
    public String getAllLessonProgress(){
        List lessonProgress=lessonProgressService.getAllLesson();
        result.success(lessonProgress);
        return ISUCCESS;
    }
}
