package com.vanda.tlzbfz.entity;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 巡查录入
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Data
@NoArgsConstructor
public class TXclr{

   // private static final long serialVersionUID = 1L;

    /**
     * 巡查编号
     */
    private String xcbh;

    /**
     * 任务编号
     */
    private String rwbh;

    /**
     * 被检查监所
     */
    private String bjcjs;

    /**
     * 巡查人
     */
    private String xcr;

    /**
     * 检查人单位
     */
    private String jcjs;

    /**
     * 巡查日期
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date xcrq;

    /**
     * 巡查描述
     */
    private String xcms;

    /**
     * 巡查类型(网上巡查，现场检查)
     */
    private String xclx;

    /**
     * 上传文件
     */
    private byte[] scwj;

    /**
     * 反馈日期
     */
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fkrq;

    /**
     * 整改状态(0未整改、1已整改)
     */
    private String zgzt;


}
