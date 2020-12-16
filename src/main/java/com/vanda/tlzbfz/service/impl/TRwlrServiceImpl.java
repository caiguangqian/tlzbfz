package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.mapper.TRwlrBeanMapper;
import com.vanda.tlzbfz.service.TRwlrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务录入 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Service
public class TRwlrServiceImpl implements TRwlrService {

    @Autowired
    private TRwlrBeanMapper tRwlrBeanMapper;

    @Override
    public int insertSelective(TRwlrBean record) {
        return tRwlrBeanMapper.insertSelective(record);
    }


    @Override
    public int updateBySelective(TRwlrBean record) {
        return tRwlrBeanMapper.updateBySelective(record);
    }

    @Override
    public List<TRwlrBean> getRwlrByCondition(String rwbh) {
        return tRwlrBeanMapper.getRwlrByCondition(rwbh);
    }

    @Override
    public int deleteByRwbh(String rwbh) {
        return tRwlrBeanMapper.deleteByRwbh(rwbh);
    }

   /* @Override
    public TRwlrBean queryRwlrByRwbh(String rwbh) {
        return tRwlrBeanMapper.queryRwlrByRwbh(rwbh);
    }*/
}
