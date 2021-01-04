package com.vanda.tlzbfz.service;


import com.vanda.tlzbfz.entity.TRwlrBean;

import java.util.List;

public interface TRwlrService  {

    int insertSelective(TRwlrBean record);
    int updateBySelective(TRwlrBean record);
    List<TRwlrBean>  getRwlrByCondition(String jsmc,String rwbh);
    int deleteByRwbh(String rwbh);
    List<TRwlrBean> selectByRwbh(String rwbh);

}
