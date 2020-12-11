package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.common.util.RandomUtil;
import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.mapper.TRwlrBeanMapper;
import com.vanda.tlzbfz.service.TRwlrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRwlrServiceImpl implements TRwlrService {

    @Autowired
    private TRwlrBeanMapper tRwlrBeanMapper;

    @Override
    public int insertSelective(TRwlrBean record) {

        RandomUtil randomUtil=new RandomUtil();
        record.setRwbh(randomUtil.getGUID());
        return tRwlrBeanMapper.insertSelective(record);
    }


    @Override
    public int updateBySelective(TRwlrBean record) {
        return tRwlrBeanMapper.updateBySelective(record);
    }

    @Override
    public List<TRwlrBean> getRwlrByCondition(TRwlrBean record) {
        return tRwlrBeanMapper.getRwlrByCondition(record);
    }


}
