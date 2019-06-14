package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Notify;
import com.dev.core.model.NotifyFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotifyService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Notify> dao;


    public void add(Notify notify){
        dao.save(notify);
    }

    public List<NotifyFormat> find(){
        List<Notify> notifyList = dao.find("from Notify where type = 1 and isEnding = 0");
        List<NotifyFormat> notifyFormatList = new ArrayList<>(notifyList.size());
        for(Notify notify : notifyList){
            NotifyFormat notifyFormat = new NotifyFormat(notify);
            notifyFormatList.add(notifyFormat);
        }
        return notifyFormatList;
    }

    public void postNotify(Notify notify){
        dao.save(notify);
    }

    public void updateNotify(Notify notify){
        String hql = "update Notify set isEnding = 1 where id = "+notify.getId();
        dao.executeHql(hql);
    }

    public void deleteNotify(ArrayList ids){
        String hql = "delete Notify where id in :ids";
        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        dao.executeHql(hql,map);
    }

    //查询变动申请
    public List<NotifyFormat> findClassNotify(){
        List<Notify> notifyList = dao.find("from Notify where type = 2 and isEnding = 0");
        List<NotifyFormat> notifyFormatList = new ArrayList<>(notifyList.size());
        for(Notify notify : notifyList){
            NotifyFormat notifyFormat = new NotifyFormat(notify);
            notifyFormatList.add(notifyFormat);
        }
        return notifyFormatList;
    }

    //查询老师评价
    public List<NotifyFormat> findEvaluateNotify(){
        List<Notify> notifyList = dao.find("from Notify where type = 3");
        List<NotifyFormat> notifyFormatList = new ArrayList<>(notifyList.size());
        for(Notify notify : notifyList){
            NotifyFormat notifyFormat = new NotifyFormat(notify);
            notifyFormatList.add(notifyFormat);
        }
        return notifyFormatList;
    }

}
