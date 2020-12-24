package com.vanda.tlzbfz.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 待办隐患
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TDbyh implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 隐患编号
     */
    private String yhbh;

    /**
     * 隐患类型
     */
    private String yhlx;

    /**
     * 隐患类别
     */
    private String yhlb;

    /**
     * 隐患细目
     */
    private String yhxm;

    /**
     * 隐患基本情况
     */
    private String yhjbqk;

    /**
     * 区域
     */
    private String qy;

    /**
     * 房室
     */
    private String fs;

    /**
     * 隐患状态
     */
    private String yhzt;

    /**
     * 巡查编号
     */
    private String xcbh;


}
