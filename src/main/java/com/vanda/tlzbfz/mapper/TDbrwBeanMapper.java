package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.CountBean;
import com.vanda.tlzbfz.entity.TDbrwBean;
import org.springframework.stereotype.Repository;

@Repository
public interface TDbrwBeanMapper {

    TDbrwBean getDbrwById(String id);
    int updateDbrw(TDbrwBean tDbrwBean);
    int inserDbrwSelective(TDbrwBean record);
    long selectCountByGW(CountBean countBean);
}