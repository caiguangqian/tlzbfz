package com.vanda.tlzbfz.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 待办任务
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Data
public class TDbrw implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 待办任务ID
     */
    private String id;

    /**
     * 任务编号
     */

    private String rwbh;
    /**
     * 岗位
     */
    private String gw;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 状态
     */
    private String zt;

    /**
     * 被检查监所
     */
    private String bjcjs;

    /**
     * 完成次数
     */
    private Long wccs;

    /**
     * 剩余次数
     */
    private Long sycs;


}
