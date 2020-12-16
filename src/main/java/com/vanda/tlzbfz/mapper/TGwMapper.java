package com.vanda.tlzbfz.mapper;


import com.vanda.tlzbfz.entity.TGw;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 岗位表 Mapper 接口
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Repository
public interface TGwMapper{
    List<TGw> selctGw();
}
