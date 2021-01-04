package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.common.config.TkMapper;
import com.vanda.tlzbfz.entity.CountBean;
import com.vanda.tlzbfz.entity.TDbrw;
import org.springframework.stereotype.Repository;

@Repository
public interface TDbrwBeanMapper extends TkMapper<TDbrw> {

    TDbrw getDbrwById(String id);
    int updateDbrw(TDbrw tDbrwBean);
    int inserDbrwSelective(TDbrw record);
    long selectCountByGW(CountBean countBean);
    int deleteById(String id);
}