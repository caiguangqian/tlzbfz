package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.TRwlrBean;

public interface TDbrwBeanMapper {

    TDbrwBean getDbrwByRwbh(String rwbh);
    int updateDbrw(TDbrwBean tDbrwBean);
    int inserDbrwSelective(TDbrwBean record);
}