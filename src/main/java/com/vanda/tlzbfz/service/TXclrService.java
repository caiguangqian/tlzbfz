package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TBjcjs;
import com.vanda.tlzbfz.entity.TXclr;

import java.util.List;

/**
 * <p>
 * 巡查录入 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
public interface TXclrService{

    int insertSelective(TXclr tXclr);
    int deleteByXcbh(String xcbh);
    int updateByXcbh(TXclr xclr);
    List<TXclr> queryXclrList(TBjcjs bjcjs);
    List<TXclr> selectAllByJcjs(TXclr xclr);
    int selectCountByExample(TXclr xclr);
}
