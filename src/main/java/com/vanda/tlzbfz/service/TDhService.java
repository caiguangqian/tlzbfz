package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TDbyh;
import com.vanda.tlzbfz.entity.TDh;

import java.util.List;

/**
 * <p>
 * 对话表 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
public interface TDhService{
    List<TDh> selectAllByxcid(String xcbh);
    int insertDh(TDh dh);
}
