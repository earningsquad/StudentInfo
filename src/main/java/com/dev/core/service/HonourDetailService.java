package com.dev.core.service;

import com.dev.core.dao.IBaseDao;
import com.dev.core.model.HonourDetail;
import com.dev.core.model.HonourDetailFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HonourDetailService {

    @Autowired
    @Qualifier("baseDao")
    IBaseDao<HonourDetail> dao;

    public void addHonourDetail(HonourDetail honourDetail){
        dao.save(honourDetail);
    }

    public List<HonourDetailFormat> showSelfHonourDetail(HonourDetail honourDetail){
        String hql = ("from HonourDetail where studentInfo.id = " +honourDetail.getStudentInfo().getId());
        List<HonourDetail> honourDetailList = dao.find(hql);
        List<HonourDetailFormat> honourDetailFormatList = new ArrayList<>(honourDetailList.size());
        for(HonourDetail h : honourDetailList){
            HonourDetailFormat honourDetailFormat = new HonourDetailFormat(h);
            honourDetailFormatList.add(honourDetailFormat);
        }
        return honourDetailFormatList;
    }
}
