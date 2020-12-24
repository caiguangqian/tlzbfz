package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.VDbyh;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDbyhMapper {
    List<VDbyh> selectDbyhByCondition(VDbyh dbyh);
}
