package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TDbyh;
import com.vanda.tlzbfz.entity.VDbyh;

import java.util.List;

/**
 * <p>
 * 待办隐患 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
public interface TDbyhService{
    List<TDbyh> selectAllDbyh(TDbyh dbyh);
    int insertDnyh(TDbyh dbyh);
    List<VDbyh> selectDbyhByCondition(VDbyh dbyh);
    int deleteBydbyh(String yhbh);
    int updateDbyh(TDbyh dbyh);
    List<TDbyh> selectDbyhByExample(TDbyh xcbh);
    long selectCountByXcbh(TDbyh dbyh);
}
