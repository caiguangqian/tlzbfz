package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.common.config.TkMapper;
import com.vanda.tlzbfz.entity.TRwlrBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TRwlrBeanMapper extends TkMapper<TRwlrBean> {

    //录入  发布单位 1用户提供   2任务编号自动生成
    int insertSelective(TRwlrBean record);
    //编辑
    int updateBySelective(TRwlrBean record);
    //列表
    List<TRwlrBean>  getRwlrByCondition(@Param("fbdw") String fbdw,@Param("rwbh") String rwbh);
    int deleteByRwbh(String rwbh);

}