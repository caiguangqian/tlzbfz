package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.*;

import java.util.List;
import java.util.Map;

public interface VDbrwService {

   /* List<VDbrw> queryDbrwList();*/
    List<VDbrw> queryDbrwByCondition(Dbrw vDbrw);

    TDbrwBean getDbrwById(String id);
    int updateDbrw(TDbrwBean tDbrwBean);
    int inserDbrwSelective(TDbrwBean record);
    long selectCountByGW(CountBean countBean);
}
