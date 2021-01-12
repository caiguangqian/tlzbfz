package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TGwtl;

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
    List<TGwtl> selctGw(String gw);
    TGwtl selectBygwdm(String gwdm);
}
