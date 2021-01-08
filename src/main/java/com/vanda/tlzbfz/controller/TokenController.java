package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.LgnUserInfo;
import com.vanda.tlzbfz.entity.TUserGw;
import com.vanda.tlzbfz.mapper.TUserGwMapper;
import com.vanda.tlzbfz.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author onion
 * @version 1.0
 * @date 2020/7/2
 * @Description
 */
@Api(value = "系统接口")
@RestController
@RequestMapping("/sys")
public class TokenController {

    private final static Logger log = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private TokenService tokenService;
    @Autowired
    private TUserGwMapper userGwMapper;

    @ApiOperation(value = "获取token", httpMethod = "GET")
    @ApiImplicitParam(name = "pk_no", value = "用户身份证", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/token",method= RequestMethod.GET)
    public ResultMsg gettoken(String pk_no) throws Exception{
        LgnUserInfo userInfo = tokenService.gettoken(pk_no);
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode("200");
        resultMsg.setMessage("获取成功!");
        resultMsg.setData(userInfo);
        return resultMsg;
    }

    /*@ApiOperation(value = "查询用户是否已绑定岗位", httpMethod = "GET")
    @ApiImplicitParam(name = "pk_no", value = "用户身份证", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/check",method= RequestMethod.GET)
    public String checkGw(String pk_no) throws Exception{
        Example example = new Example(TUserGw.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pk_no",pk_no);
        List<TUserGw> list = userGwMapper.selectByExample(example);
        if (list.size()>0){
            return "1";
        }
        return "0";
    }*/

    @ApiOperation(value = "用户手动绑定岗位", httpMethod = "GET")
    @GetMapping("/userGw")
    public ResultMsg bindGw(String pk_no,String gwdm){
        ResultMsg resultMsg=new ResultMsg();
        Example example = new Example(TUserGw.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pk_no",pk_no);
        int count = userGwMapper.selectCountByExample(example);
        if(count>0){
           resultMsg.setCode("201");
           resultMsg.setMessage("岗位已绑定，请勿重复绑定！");
           return resultMsg;
        }
        TUserGw userGw = new TUserGw();
        userGw.setId(String.valueOf(System.currentTimeMillis()));
        userGw.setPk_no(pk_no);
        userGw.setGwdm(gwdm);
        userGwMapper.insert(userGw);
        LgnUserInfo token = tokenService.gettoken(pk_no);

        resultMsg.setCode("200");
        resultMsg.setMessage("设置岗位成功！");
        resultMsg.setData(token.getToken());
        return resultMsg;
    }

}
