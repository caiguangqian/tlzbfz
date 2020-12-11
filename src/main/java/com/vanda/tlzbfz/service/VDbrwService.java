package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.Dbrw;
import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.VDbrw;

import java.util.List;
import java.util.Map;

public interface VDbrwService {

   /* List<VDbrw> queryDbrwList();*/
    List<VDbrw> queryDbrwByCondition(Dbrw vDbrw);

    TDbrwBean getDbrwByRwbh(String rwbh);
    int updateDbrw(TDbrwBean tDbrwBean);
}
