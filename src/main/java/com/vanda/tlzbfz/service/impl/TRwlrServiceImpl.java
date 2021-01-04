package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.mapper.TRwlrBeanMapper;
import com.vanda.tlzbfz.service.TRwlrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public List<TRwlrBean> getRwlrByCondition(String jsmc,String rwbh) {
        return tRwlrBeanMapper.getRwlrByCondition(jsmc,rwbh);
    }

    @Override
    public int deleteByRwbh(String rwbh) {
        return tRwlrBeanMapper.deleteByRwbh(rwbh);
    }

    @Override
    public List<TRwlrBean> selectByRwbh(String rwbh) {
        Example example = new Example(TRwlrBean.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("rwbh",rwbh);
        return tRwlrBeanMapper.selectByExample(example);
    }

   /* @Override
    public TRwlrBean queryRwlrByRwbh(String rwbh) {
        return tRwlrBeanMapper.queryRwlrByRwbh(rwbh);
    }*/
}
