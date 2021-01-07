package com.vanda.tlzbfz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TRwlrBean implements Serializable {

    private String rwbh;

    private String rwjc;

    private Long yqcs;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date ksrq;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date jsrq;

    private String rwlx;

    private String rwsm;

    private String fbdw;

    private byte[] scwj;


}