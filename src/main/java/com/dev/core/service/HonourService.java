package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Honour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;

@Service
public class HonourService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<Honour> dao;

    //查询荣誉
    public List<Honour> findHonour(){
        return dao.find("from Honour");
    }

    //条件查询荣誉
    public List<Honour> findHonourBy(Honour honour){
        StringBuffer hql = new StringBuffer("from Honour where 1 = 1  " );
        if(honour.getType()!=0){
            hql.append(" and type = " + honour.getType());
        }
        if(honour.getName() != null && honour.getName().length()!=0){
            hql.append(" and name like '%" + honour.getName() + "%'");
        }
        if(honour.getDetail() != null && honour.getDetail().length()!=0){
            hql.append(" and detail like '%" + honour.getDetail() + "%'");
        }
        return dao.find(hql.toString());
    }


    //新增荣誉
    public void addHonour(Honour honour){
        dao.save(honour);
    }

    //删除荣誉
    public void deleteHonur(Honour honour){
        dao.delete(honour);
    }

}
