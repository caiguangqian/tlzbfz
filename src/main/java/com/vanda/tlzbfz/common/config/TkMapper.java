package com.vanda.tlzbfz.common.config;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * tk.mybatis的mapper接口
 * 特别注意，该接口不能被扫描到，否则会出错
 * 方便简单的单表查询
 * @author onion
 */

public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}