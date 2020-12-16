package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.SystemLoginUser;
import com.vanda.tlzbfz.entity.TBjcjs;
import com.vanda.tlzbfz.entity.TGw;
import com.vanda.tlzbfz.service.TBjcjsService;
import com.vanda.tlzbfz.service.TGwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@Api(value = "岗位查询接口")
@RestController
@RequestMapping("/t-gw")
public class TGwController {
    @Autowired
    private TGwService tGwService;
    @Autowired
    GsonUtil gsonUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TBjcjsService bjcjsService;

    //查询列表  条件查询 都使用该接口
    @ApiOperation(value = "查询所有岗位", httpMethod = "GET")
    @GetMapping("/getGw")
    public ResultMsg getGw(@RequestHeader("accept_token") String accept_token){
       /* SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        //狗日的换行符.jsmc是被检查监所
        String jsmc = bjcjs.getJsmc();
        jsmc=jsmc.replaceAll("\n","");
        //获取用户岗位代码
        String gw = user.getPost();*/

        List<TGw> list = tGwService.selctGw();
        if(list==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",list);
    }

}
