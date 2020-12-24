package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.entity.TBjcjs;
import com.vanda.tlzbfz.mapper.TBjcjsMapper;
import com.vanda.tlzbfz.service.TBjcjsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 被检查监所 服务实现类
 * </p>
 *
 * @author onion
 * @since 2020-12-15
 */
@Service
public class TBjcjsServiceImpl implements TBjcjsService {

    @Autowired
    TBjcjsMapper bjcjsMapper;

    @Override
    public TBjcjs selectBjcjs(String jsdm) {
        return bjcjsMapper.selectBjcjs(jsdm);
    }

    @Override
    public List<TBjcjs> selectJslike(String jsl) {
        return bjcjsMapper.selectJslike(jsl);
    }
}
