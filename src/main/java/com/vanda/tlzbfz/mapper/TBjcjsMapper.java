package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.common.config.TkMapper;
import com.vanda.tlzbfz.entity.TBjcjs;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 被检查监所 Mapper 接口
 * </p>
 *
 * @author onion
 * @since 2020-12-15
 */
@Repository
public interface TBjcjsMapper extends TkMapper<TBjcjs> {

    TBjcjs selectBjcjs(String jsdm);
    List<TBjcjs> selectJslike(String jsl);
}
