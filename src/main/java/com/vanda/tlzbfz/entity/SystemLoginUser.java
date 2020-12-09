package com.vanda.tlzbfz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统登录用户
 * @author zcy
 * @version 1.0, 2018/05/19
 */
@Data
@NoArgsConstructor
public class SystemLoginUser implements java.io.Serializable{

    private static final long serialVersionUID = -8113202283747834696L;

    /**
     * 用户真实姓名
     */
    private String name;
    /**
     * 用户身份证号
     */
    private String cardNumber;
    /**
     * 用户单位编码
     */
    private String[] unitCode;
    /**
     * 用户工作岗位
     */
    private String post;
    private String gw;
    /**
     * 登录IP地址
     */
    private String ipAddr;
    /**
     * 特别权限
     */
    private String specialPolicy;

    /**
     * 当前操作单位代码，合设所用户使用
     */
    private String currentUnitCode;

    /**
     * 用户密码MD5
     */
    private String initPass;

    /**
     * 监所类型，使用省厅、地市用户
     */
    private String unitType;


    private String btime;

    private String etime;

    private String tablename;

    private String showname;

    private Integer num;





    public SystemLoginUser(String unitCode){
        this.setUnitCode(new String[]{unitCode});
        this.setCurrentUnitCode(unitCode);
    }

}
