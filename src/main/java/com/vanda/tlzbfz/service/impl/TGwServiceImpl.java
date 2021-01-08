package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TGwtl;
import com.vanda.tlzbfz.mapper.TGwMapper;
import com.vanda.tlzbfz.service.TGwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Service
public class TGwServiceImpl  implements TGwService {

    @Autowired
    TGwMapper gwMapper;
    @Override
    public List<TGwtl> selctGw() {
        return gwMapper.selctGw();
    }

    @Override
    public TGwtl selectBygwdm(String gwdm) {
        Example example = new Example(TGwtl.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gwdm",gwdm);
        TGwtl gw = (TGwtl) gwMapper.selectOneByExample(example);
        return gw;
    }
}
