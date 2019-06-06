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
import java.util.List;

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


}
