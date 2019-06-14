package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.Honour;
import com.dev.core.model.HonourDetail;
import com.dev.core.model.HonourFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<HonourFormat> findHonourBy(Honour honour, int stuId){

        String sql = "SELECT id FROM honour WHERE id NOT IN" +
                "(SELECT h.id FROM student_info stu LEFT JOIN honour_detail hd on stu.id = hd.STUDENT_ID " +
                "LEFT JOIN honour h ON hd.HONOUR_ID = h.id WHERE stu.id = "+ stuId +" )";

        List<Map> list = dao.findBySql(sql);

        List<Integer> ids = new ArrayList<>();
        for(int i = 0;i < list.size();i++){
            ids.add((Integer) list.get(i).get("id"));
        }

        Map<String,Object> map = new HashMap<>();
        map.put("ids",ids);


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
        if(ids.size()!=0){
            hql.append(" and id in :ids");
        }
        List<Honour> honourList = dao.find(hql.toString(),map);
        List<HonourFormat> honourFormatList = new ArrayList<>(honourList.size());
        for(Honour honour1 : honourList){
            HonourFormat honourFormat = new HonourFormat(honour1);
            honourFormatList.add(honourFormat);
        }
        return honourFormatList;
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
