package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.Dbrw;
import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.entity.VDbrw;
import com.vanda.tlzbfz.mapper.TDbrwBeanMapper;
import com.vanda.tlzbfz.mapper.VDbrwMapper;
import com.vanda.tlzbfz.service.VDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VDbrwServiceImp implements VDbrwService {

    @Autowired
    private VDbrwMapper vDbrwMapper;

    @Autowired
    private TDbrwBeanMapper tDbrwBeanMapper;


   /* @Override
    public List<VDbrw> queryDbrwList() {
        return vDbrwMapper.queryDbrwList();
    }*/

    @Override
    public List<VDbrw> queryDbrwByCondition(Dbrw vDbrw) {
        return vDbrwMapper.queryDbrwByCondition(vDbrw);
    }

    @Override
    public TDbrwBean getDbrwByRwbh(String rwbh) {
        return tDbrwBeanMapper.getDbrwByRwbh(rwbh);
    }

    @Override
    public int updateDbrw(TDbrwBean tDbrwBean) {
        return tDbrwBeanMapper.updateDbrw(tDbrwBean);
    }

    @Override
    public int inserDbrwSelective(TDbrwBean record) {
        return tDbrwBeanMapper.inserDbrwSelective(record);
    }
}
