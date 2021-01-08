package com.vanda.tlzbfz.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Data
public class TGwtl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位代码
     */
    private String gwdm;

    /**
     * 岗位名称
     */
    private String gwmc;


}
