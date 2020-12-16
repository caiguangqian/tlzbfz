package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TGw;
import com.vanda.tlzbfz.mapper.TGwMapper;
import com.vanda.tlzbfz.service.TGwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<TGw> selctGw() {
        return gwMapper.selctGw();
    }
}
