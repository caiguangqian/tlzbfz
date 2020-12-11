package com.vanda.tlzbfz.mapper;


import com.vanda.tlzbfz.entity.VDbrw;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VDbrwMapper {

    List<VDbrw> queryDbrwByCondition(VDbrw vDbrw);



}