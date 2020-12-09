package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.TDbrw;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 待办任务 服务类
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
public interface TDbrwService{
    List<TDbrw> selectAll();
}
