package com.vanda.tlzbfz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName VDbyh
 * @Description: TODO
 * @Author onion
 * @Date 2020/12/21
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class VDbyh {
    private String yid;
    private String bjcjs;
    private String jcjs;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date xcrq;
    private String ksrq;
    private String jsrq;
    private String xcr;
    private String qy;
    private String fs;
    private String yhlx;
    private String yhlb;
    private String yhxm;
    private String yhjbqk;
    private String xclx;
    private String yhzt;
    private String xcbh;
}
