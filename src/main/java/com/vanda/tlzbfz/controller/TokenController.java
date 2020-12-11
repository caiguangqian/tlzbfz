package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    @ApiOperation(value = "获取token", httpMethod = "GET")
    @ApiImplicitParam(name = "pk_no", value = "用户名", paramType = "query", required = true, dataType = "String")
    @RequestMapping(value = "/gettoken",method= RequestMethod.GET)
    public String gettoken(String user_name) throws Exception{
        log.debug("user_name");
        return tokenService.gettoken(user_name);
    }

}
