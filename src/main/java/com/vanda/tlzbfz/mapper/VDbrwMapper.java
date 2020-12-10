package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.VDbrw;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDbrwMapper {
   /* //列表
    List<VDbrw> queryDbrwList();*/
    //条件过滤
    List<VDbrw> queryDbrwByCondition(VDbrw vDbrw);
    //通过指定字段查询一条记录
    //编辑
    //int updateBySelective(TDbrwBean record);


}