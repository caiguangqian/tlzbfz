package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.common.config.TkMapper;
import com.vanda.tlzbfz.entity.TDbyh;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 待办隐患 Mapper 接口
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Repository
public interface TDbyhMapper extends TkMapper<TDbyh> {

    int deleteBydbyh(String dbyh);
    int updateDbyhZt(TDbyh dbyh);
}
