package com.vanda.tlzbfz.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 对话表
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDh implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 人员姓名
     */
    private String xm;

    /**
     * 所在单位
     */
    private String szdw;

    /**
     * 对话时间
     */
    private Date dhsj;

    /**
     * 对话内容
     */
    private String dhnr;


    /**
     * 是否结束会话
     */
    private String zt;

    /**
     * 巡查录入ID
     */
    private String xcbh;


}
