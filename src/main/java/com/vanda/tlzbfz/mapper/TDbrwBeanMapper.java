package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.TDbrwBean;

public interface TDbrwBeanMapper {

    TDbrwBean getDbrwByRwbh(String rwbh);
    int updateDbrw(TDbrwBean tDbrwBean);
}