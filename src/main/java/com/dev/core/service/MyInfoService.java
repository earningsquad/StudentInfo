package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MyInfoService {
    @Autowired
    IBaseDao baseDao;

    //更新我的基本信息
    @Transactional
    public void update(MyBasicInfo myBasicInfo){
       StudentInfo studentInfo= (StudentInfo) baseDao.getById(StudentInfo.class,myBasicInfo.getStudentId());
       myBasicInfo.getStudentInfoE(studentInfo);
       // baseDao.update(studentInfo.getUser());
       baseDao.update(studentInfo);

    }
    //查看我的基本信息
    public MyBasicInfo getBasic(int uid){
     List list= (List) baseDao.findByHqL("from StudentInfo where uid="+uid);
        if (list!=null&&list.size()>0){
            //MyWholeInfo myWholeInfo= (MyWholeInfo) list.get(0);
            StudentInfo studentInfo= (StudentInfo) list.get(0);
            return studentInfo.getMyBasicInfo();
        }
    return null;
    }
    //查看我的全部信息
    public StudentInfo getWholeInfo(int uid){
        List list= (List) baseDao.findByHqL("from StudentInfo where uid="+uid);
        if (list!=null&&list.size()>0){
            //MyWholeInfo myWholeInfo= (MyWholeInfo) list.get(0);
            StudentInfo studentInfo= (StudentInfo) list.get(0);
            return studentInfo;
        }
        return null;
    }

    //查看我的成绩
    public List<StuLesson> getMyScores(int stuId){
        List<StuLesson> list= (List) baseDao.findByHqL("from StuLesson where STUDENT_ID="+stuId);
        return list;
    }

    //查看我的荣誉
    public   List<HonourDetail> getMyHonour(int stuId){
        List<HonourDetail> list= (List) baseDao.findByHqL("from HonourDetail where STUDENT_ID="+stuId);
        return list;
    }

}
