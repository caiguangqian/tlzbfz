package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TGw;

import java.util.List;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
public interface TGwService  {
    List<TGw> selctGw();
    TGw selectBygwdm(String gwdm);
}
