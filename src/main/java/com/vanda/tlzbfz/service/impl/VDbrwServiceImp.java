package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.mapper.TDbrwBeanMapper;
import com.vanda.tlzbfz.mapper.VDbrwMapper;
import com.vanda.tlzbfz.service.VDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class VDbrwServiceImp implements VDbrwService {

    @Autowired
    private VDbrwMapper vDbrwMapper;

    @Autowired
    private TDbrwBeanMapper tDbrwBeanMapper;
    @Autowired
    private TDbrwBeanMapper dbrwBeanMapper;

    @Override
    public List<VDbrw> queryDbrwByCondition(Dbrw vDbrw) {
        return vDbrwMapper.queryDbrwByCondition(vDbrw);
    }

    @Override
    public TDbrw getDbrwById(String id) {
        return tDbrwBeanMapper.getDbrwById(id);
    }

    @Override
    public int updateDbrw(TDbrw tDbrwBean) {
        return tDbrwBeanMapper.updateDbrw(tDbrwBean);
    }

    @Override
    public int inserDbrwSelective(TDbrw record) {
        return tDbrwBeanMapper.inserDbrwSelective(record);
    }

    @Override
    public long selectCountByGW(CountBean countBean) {
        long count = tDbrwBeanMapper.selectCountByGW(countBean);
        return count;
    }

    @Override
    public List<TDbrw> selectByRwbh(String rwbh) {
        Example example = new Example(TDbrw.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("rwbh",rwbh);
        return dbrwBeanMapper.selectByExample(example);
    }

    @Override
    public int deleteById(String id) {
        return dbrwBeanMapper.deleteById(id);
    }
}
