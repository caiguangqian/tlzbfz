package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TBjcjs;
import com.vanda.tlzbfz.entity.TXclr;
import com.vanda.tlzbfz.mapper.TXclrMapper;
import com.vanda.tlzbfz.service.TXclrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 巡查录入 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Service
public class TXclrServiceImpl implements TXclrService {

    @Autowired
    private TXclrMapper xclrMapper;
    @Override
    public int insertSelective(TXclr tXclr) {
        return xclrMapper.insertXclrSelective(tXclr);
    }

    @Override
    public int deleteByXcbh(String xcbh) {
        return xclrMapper.deleteByXcbh(xcbh);
    }

    @Override
    public int updateByXcbh(TXclr xclr) {
        return xclrMapper.updateByKey(xclr);
    }

    @Override
    public List<TXclr> queryXclrList(TBjcjs bjcjs) {
        Example example = new Example(TXclr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("jcjs",bjcjs.getJsmc());
        criteria.orEqualTo("bjcjs",bjcjs.getJsmc());
        return xclrMapper.selectByExample(example);
    }

    @Override
    public List<TXclr> selectAllByJcjs(TXclr xclr) {
        Example example = new Example(TXclr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bjcjs",xclr.getBjcjs());
        return xclrMapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(TXclr xclr) {
        return xclrMapper.selectCountByExample(xclr);
    }
}
