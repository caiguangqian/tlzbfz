package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.TXclr;
import com.vanda.tlzbfz.common.config.TkMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 巡查录入 Mapper 接口
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Repository
public interface TXclrMapper extends TkMapper<TXclr>{
    int deleteByXcbh(String xcbh);
    int updateByKey(TXclr xclr);
}
