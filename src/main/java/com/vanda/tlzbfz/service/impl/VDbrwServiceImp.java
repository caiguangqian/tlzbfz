package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.*;
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

    @Override
    public List<VDbrw> queryDbrwByCondition(Dbrw vDbrw) {
        return vDbrwMapper.queryDbrwByCondition(vDbrw);
    }

    @Override
    public TDbrwBean getDbrwById(String id) {
        return tDbrwBeanMapper.getDbrwById(id);
    }

    @Override
    public int updateDbrw(TDbrwBean tDbrwBean) {
        return tDbrwBeanMapper.updateDbrw(tDbrwBean);
    }

    @Override
    public int inserDbrwSelective(TDbrwBean record) {
        return tDbrwBeanMapper.inserDbrwSelective(record);
    }

    @Override
    public long selectCountByGW(CountBean countBean) {
        long count = tDbrwBeanMapper.selectCountByGW(countBean);
        return count;
    }
}
