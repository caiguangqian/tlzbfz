package com.vanda.tlzbfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VDbrw implements java.io.Serializable{
    private static final long serialVersionUID = -8113202283747834696L;

    private String id;

    private String rwjc;

    private String bjcjs;

    private String gwmc;

    private String gwdm;

    private String xm;

    private String rwsm;

    private String rwlx;

    private Integer yqcs;

    private Integer wccs;

    private Integer sycs;

    private String zt;

    private String rwbh;

    private Date ksrq;

    private Date jsrq;

    private String fbdw;

    private String fbgw;

    //private String isGly;

}