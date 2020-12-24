package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TDh;
import com.vanda.tlzbfz.mapper.TDhMapper;
import com.vanda.tlzbfz.service.TDhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 对话表 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Service
public class TDhServiceImpl  implements TDhService {

    @Autowired
    private TDhMapper dhMapper;
    @Override
    public List<TDh> selectAllByxcid(String xcbh) {

        Example example = new Example(TDh.class);
        example.setOrderByClause("DHSJ ASC");
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("xcbh",xcbh);
        return dhMapper.selectByExample(example);
    }

    @Override
    public int insertDh(TDh dh) {
        return dhMapper.insertSelective(dh);
    }
}
