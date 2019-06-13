package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Task;
import com.dev.core.model.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService {

    @Autowired
    IBaseDao baseDao;

    public  List<TeacherInfo>  getTeacherInfo(int uid){
     List<TeacherInfo> teacherInfos= baseDao.find("from TeacherInfo t where t.user.id="+uid);
        return teacherInfos;
    }
    public List<Task> getTaskInfo(int tid){
        List<Task> tasks= baseDao.find("from Task t where t.teacherInfo.id="+tid);
        return tasks;
    }
    public void  saveTask(Task t){
        baseDao.save(t);
    }
}
