package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TDbrw;
import com.vanda.tlzbfz.mapper.TDbrwMapper;
import com.vanda.tlzbfz.service.TDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 待办任务 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Service
public class TDbrwServiceImpl  implements TDbrwService {

    @Autowired
    private TDbrwMapper tDbrwMapper;

    @Override
    public List<TDbrw> selectAll() {
        return tDbrwMapper.selectAll();
    }
}
