package com.vanda.tlzbfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TRwlrBean {

    private String rwbh;

    private String rwjc;

    private Long yqcs;

    private Date ksrq;

    private Date jsrq;

    private String rwlx;

    private String rwsm;

    private String fbdw;

    private byte[] scwj;

}