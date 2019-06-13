package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Timetable> dao;

    //查找老师课表
    public  List<List<String>> getTimetableBySid(int sid){
        List<List<String>> resultList = new ArrayList<>();
        for(int i = 1;i <= 7;i++){
            String hql = "FROM Timetable t where t.lesson.id in (SELECT t.lesson.id  from StuLesson  st where st.studentInfo.id="+sid+") and t.week="+i+" order by num";
            List<Timetable> timetables = dao.find(hql);
            List<String> stringList = new ArrayList<>();
            if(timetables.size() == 0){
                while(stringList.size()!=11){
                    stringList.add("");
                }
            }else{
                while(stringList.size()!=12){
                    int j = 0;
                    if(timetables.get(j).getNum() != stringList.size()){
                        stringList.add("");
                    }else {
                        stringList.add(timetables.get(j).getLesson().getLeName()+"@"+timetables.get(j).getAddress());
                        j++;
                    }
                }
            }

            resultList.add(stringList);
        }
        return resultList;
    }

}
