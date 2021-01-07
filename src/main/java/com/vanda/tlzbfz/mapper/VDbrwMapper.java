package com.vanda.tlzbfz.mapper;


import com.vanda.tlzbfz.common.config.TkMapper;
import com.vanda.tlzbfz.entity.VDbrw;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VDbrwMapper extends TkMapper<VDbrw> {

    List<VDbrw> queryDbrwByCondition(VDbrw vDbrw);
    List<VDbrw> queryDbrwByConditionG(VDbrw vDbrw);


}