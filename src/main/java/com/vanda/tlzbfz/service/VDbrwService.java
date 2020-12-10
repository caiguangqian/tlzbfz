package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.VDbrw;

import java.util.List;

public interface VDbrwService {

   /* List<VDbrw> queryDbrwList();*/
    List<VDbrw> queryDbrwByCondition(VDbrw vDbrw);

    TDbrwBean getDbrwByRwbh(String rwbh);
    int updateDbrw(TDbrwBean tDbrwBean);
}
