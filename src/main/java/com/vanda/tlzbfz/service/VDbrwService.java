package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.*;

import java.util.List;

public interface VDbrwService {

   /* List<VDbrw> queryDbrwList();*/
    List<VDbrw> queryDbrwByCondition(Dbrw vDbrw);
    List<VDbrw> queryDbrwByConditionG(Dbrw dbrw);
    List<VDbrw> selectAll();

    TDbrw getDbrwById(String id);
    int updateDbrw(TDbrw tDbrwBean);
    int inserDbrwSelective(TDbrw record);
    long selectCountByGW(CountBean countBean);
    List<TDbrw> selectByRwbh(String rwbh);
    int deleteById(String id);
}
