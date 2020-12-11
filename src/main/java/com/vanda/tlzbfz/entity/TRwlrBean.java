package com.vanda.tlzbfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TRwlrBean {

    private String rwbh;

    private String rwjc;

    private BigDecimal yqcs;

    private Date ksrq;

    private Date jsrq;

    private String rwlx;

    private String rwsm;

    private String fbdw;

    private String scwj;


}