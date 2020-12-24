package com.vanda.tlzbfz.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 被检查监所
 * </p>
 *
 * @author onion
 * @since 2020-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TBjcjs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 监所代码
     */
    private String jsdm;

    /**
     * 监所名称
     */
    private String jsmc;


}
