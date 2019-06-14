package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Comment;
import com.dev.core.model.Lesson;
import com.dev.core.model.LessonFormat;
import com.dev.core.utils.JsonResult;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LessonService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Lesson> dao;

    public void addLesson(Lesson lesson){
        dao.save(lesson);
    }

    public void deleteLesson(Lesson lesson){
        dao.delete(lesson);
    }

    public void updateLesson(Lesson lesson){
        dao.update(lesson);
    }

    public List<LessonFormat> findLesson(){
        List<Lesson>  lessonList = dao.find("from Lesson");
        List<LessonFormat> lessonFormatList = new ArrayList<>(lessonList.size());
        for(Lesson lesson : lessonList){
            lessonFormatList.add(new LessonFormat(lesson));
        }

        return lessonFormatList;
    }

    //查询该生还没有选择的课程
    public List<LessonFormat> findUnSelectLesson(int stuId){

        String sql = "SELECT id FROM LESSON WHERE id NOT IN" +
                "(SELECT le.id FROM stu_lesson sl " +
                "  LEFT JOIN lesson le ON sl.LESSON_ID = le.id WHERE sl.STUDENT_ID =  "+ stuId +" )";

        List<Map> list = dao.findBySql(sql);
        List<Lesson>  lessonList = new ArrayList<>();

        List<Integer> ids = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            ids.add((Integer) list.get(i).get("id"));
        }
        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);

        if(ids.size() == 0){
            return new ArrayList<LessonFormat>();
        }

        lessonList = dao.find("from Lesson WHERE id in :ids",map);
        List<LessonFormat> lessonFormatList = new ArrayList<>(lessonList.size());
        for(Lesson lesson : lessonList){
            lessonFormatList.add(new LessonFormat(lesson));
        }

        return lessonFormatList;
    }

    //查询已经选的课程
    public List<LessonFormat> findSelectLesson(int stuId){

        String sql = "SELECT le.id FROM stu_lesson sl " +
                " LEFT JOIN lesson le ON sl.LESSON_ID = le.id WHERE sl.STUDENT_ID =  "+ stuId ;

        List<Map> list = dao.findBySql(sql);

        List<Integer> ids = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            ids.add((Integer) list.get(i).get("id"));
        }

        if(ids.size() == 0){
            return new ArrayList<LessonFormat>();
        }

        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);

        List<Lesson>  lessonList = dao.find("from Lesson WHERE id in :ids",map);
        List<LessonFormat> lessonFormatList = new ArrayList<>(lessonList.size());
        for(Lesson lesson : lessonList){
            lessonFormatList.add(new LessonFormat(lesson));
        }

        return lessonFormatList;
    }

    //更新课程容量
    public void updateLessonTotal(int id){
        dao.executeSql("UPDATE LESSON SET TOTAL = TOTAL - 1 WHERE ID = " + id);
    }


}
