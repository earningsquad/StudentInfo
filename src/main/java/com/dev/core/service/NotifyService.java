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
        List<Notify> notifyList = dao.find("from Notify");
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

    public void deleteNotify(ArrayList ids){
        String hql = "delete Notify where id in :ids";
        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);
        dao.executeHql(hql,map);
    }

}
