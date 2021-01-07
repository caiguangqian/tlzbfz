package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TBjcjs;

import java.util.List;

/**
 * <p>
 * 被检查监所 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-15
 */
public interface TBjcjsService{

    TBjcjs selectBjcjs(String jsdm);
    List<TBjcjs> selectJslike(String jsl);
    List<TBjcjs> selectAllJs();
}
