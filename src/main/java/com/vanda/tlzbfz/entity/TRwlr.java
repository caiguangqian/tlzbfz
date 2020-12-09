package com.vanda.tlzbfz.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 任务录入
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Data
public class TRwlr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号
     */
    private String rwbh;

    /**
     * 待办任务id
     */
    /*private String dbrwId;*/

    /**
     * 任务简称
     */
    private String rwjc;

    /**
     * 要求次数
     */
    private String yqcs;

    /**
     * 开始日期
     */
    private LocalDateTime ksrq;

    /**
     * 结束日期
     */
    private LocalDateTime jsrq;

    /**
     * 任务类型
     */
    private String rwlx;

    /**
     * 任务说明
     */
    private String rwsm;

    /**
     * 发布单位
     */
    private String fbdw;

    /**
     * 上传文件
     */
    private String scwj;

    private List<TDbrw> dbrws;


}
