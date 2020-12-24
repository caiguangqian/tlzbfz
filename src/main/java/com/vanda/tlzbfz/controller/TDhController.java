package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.MapperUtils;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.RwlrExtendBean;
import com.vanda.tlzbfz.entity.SystemLoginUser;
import com.vanda.tlzbfz.entity.TDh;
import com.vanda.tlzbfz.service.TDhService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 对话表 前端控制器
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Api(value = "处理进度对话接口")
@RestController
@RequestMapping("/tdh")
public class TDhController {

    @Autowired
    private TDhService dhService;
    @Autowired
    GsonUtil gsonUtil;
    @Autowired
    private RedisUtil redisUtil;

    private final static Logger log = LoggerFactory.getLogger(TRwlrController.class);
    //查询列表  条件查询 都使用该接口
    @ApiOperation(value = "根据巡查编号查询所有处理进度对话", httpMethod = "GET")
    @GetMapping("/dhs")
    public ResultMsg getAllDhByxcid(String xcbh , @RequestHeader("accept_token") String accept_token){
        List<TDh> list = dhService.selectAllByxcid(xcbh);
        if(list==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",list);
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "录入对话内容接口", httpMethod = "POST")
    @PostMapping("/dh")
    public ResultMsg insertDh(@RequestBody String json,@RequestHeader("accept_token") String accept_token) throws Exception {
        try {
            /*Gson gson = gsonUtil.createGson();
            TDh dh = gson.fromJson(json, TDh.class);*/
            TDh dh =MapperUtils.json2pojo(json,TDh.class);
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String id = String.valueOf(System.currentTimeMillis());
            dh.setId(id.substring(6,id.length()));
            String[] dwdm = user.getUnitCode();
            dh.setSzdw(dwdm[0]);
            dh.setXm(user.getName());
            dh.setDhsj(new Date());
            log.info("@@"+String.valueOf(dh));
            int resulet = dhService.insertDh(dh);
            ResultMsg resultMsg=new ResultMsg();
            if(resulet<0){
                resultMsg.setCode("400");
                resultMsg.setMessage("录入对话为空");
            }else {
                resultMsg.setCode("200");
                resultMsg.setMessage("录入对话成功");
            }
            return resultMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("录入对话记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }

}
