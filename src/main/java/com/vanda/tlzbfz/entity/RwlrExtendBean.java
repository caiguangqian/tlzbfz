package com.vanda.tlzbfz.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RwlrExtendBean
 * @Description: TODO
 * @Author onion
 * @Date 2020/12/10
 * @Version V1.0
 **/
@Data
public class RwlrExtendBean extends TRwlrBean{
    private List<TDbrwBean> tdbrwBeans;
}
