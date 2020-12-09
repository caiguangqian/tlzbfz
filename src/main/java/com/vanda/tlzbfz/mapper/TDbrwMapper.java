package com.vanda.tlzbfz.mapper;


import com.vanda.tlzbfz.entity.TDbrw;
import com.vanda.tlzbfz.mybatis.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 待办任务 Mapper 接口
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Repository
public interface TDbrwMapper extends MyMapper<TDbrw> {
    List<TDbrw> selectAll();
}
