package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TDbyh;
import com.vanda.tlzbfz.entity.VDbyh;
import com.vanda.tlzbfz.mapper.TDbyhMapper;
import com.vanda.tlzbfz.mapper.VDbyhMapper;
import com.vanda.tlzbfz.service.TDbyhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 待办隐患 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Service
public class TDbyhServiceImpl implements TDbyhService {

    @Autowired
    private TDbyhMapper tDbyhMapper;
    @Autowired
    private VDbyhMapper dbyhMapper;
    @Override
    public List<TDbyh> selectAllDbyh(TDbyh dbyh) {
        Example example = new Example(TDbyh.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("szdw",dbyh.getXcbh());
        return tDbyhMapper.selectAll();
    }

    @Override
    public int insertDnyh(TDbyh dbyh) {
        return tDbyhMapper.insertSelective(dbyh);
    }

    @Override
    public List<VDbyh> selectDbyhByCondition(VDbyh dbyh) {
        return dbyhMapper.selectDbyhByCondition(dbyh);
    }

    @Override
    public int deleteBydbyh(String yhbh) {
        return tDbyhMapper.deleteBydbyh(yhbh);
    }

    @Override
    public int updateDbyh(TDbyh dbyh) {
        return tDbyhMapper.updateDbyhZt(dbyh);
    }

    @Override
    public List<TDbyh> selectDbyhByExample(TDbyh dbyh) {
        Example example = new Example(TDbyh.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("xcbh",dbyh.getXcbh());
        return tDbyhMapper.selectByExample(example);
    }

    @Override
    public long selectCountByXcbh(TDbyh dbyh) {
        Example example = new Example(TDbyh.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("xcbh",dbyh.getXcbh());
        return tDbyhMapper.selectCountByExample(example);
    }
}
