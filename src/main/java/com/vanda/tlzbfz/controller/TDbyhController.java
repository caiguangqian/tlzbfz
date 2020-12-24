package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.service.TBjcjsService;
import com.vanda.tlzbfz.service.TDbyhService;
import com.vanda.tlzbfz.service.TXclrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 待办隐患 前端控制器
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Api(value = "隐患接口")
@RestController
@RequestMapping("/tdbyh")
public class TDbyhController {

    @Autowired
    private TDbyhService dbyhService;
    @Autowired
    private GsonUtil gsonUtil;
    @Autowired
    private TXclrService xclrService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TBjcjsService bjcjsService;
    private static final Logger log = LoggerFactory.getLogger(TDbyhController.class);

    @ApiOperation(value = "根据条件查询待办隐患池,台账功能", httpMethod = "GET")
    @GetMapping("/dbyhs")
    public ResultMsg getAllDhyh(VDbyh dbyh, @RequestHeader("accept_token") String accept_token){

        List<VDbyh> list = dbyhService.selectDbyhByCondition(dbyh);
        if(list==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",list);
    }

   /* @ApiOperation(value = "查询本岗位待办隐患记录数", httpMethod = "GET")
    @GetMapping("/dbyhCount")
    public ResultMsg getAllDhyhCounts(@RequestHeader("accept_token") String accept_token){
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        TDbyhExtenBean dbyhExtenBean = new TDbyhExtenBean();
        dbyhExtenBean.setBjcjs(bjcjs.getJsdm());
        //查询某个单位待办巡查数
        //根据待办巡查数查询每个巡查id下的待办隐患数

        long count = dbyhService.selectCountByXcbh(dbyhExtenBean);
        List<VDbyh> list=null;
        if(list==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",list);
    }*/


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "根据待办隐患编号删除一条记录接口", httpMethod = "DELETE")
    @DeleteMapping("/dbyh")
    public ResultMsg deleteBydbyh(@RequestParam("yhbh") String yhbh){
        try {
            int xclrBean = dbyhService.deleteBydbyh(yhbh);
            ResultMsg rMsg=new ResultMsg();
            if(xclrBean<=0){
                rMsg.setCode("400");
                rMsg.setMessage("删除隐患记录为空");
            }else {
                rMsg.setCode("200");
                rMsg.setMessage("成功删除一条隐患记录");
            }
            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("删除隐患记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "更新待办隐患状态接口", httpMethod = "PUT")
    @PutMapping("/dbyh")
    public  ResultMsg updateDbyhZt(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        try {
            Gson gson = gsonUtil.createGson();
            TDbyh record = gson.fromJson(json,TDbyh.class);
            record.setYhzt("1");
            ResultMsg rMsg=new ResultMsg();
            int rel = dbyhService.updateDbyh(record);

            if(rel<=0){
                rMsg.setCode("201");
                rMsg.setMessage("隐患信息更新失败");
            }else {
                rMsg.setCode("200");
                rMsg.setMessage("隐患信息更新成功");
            }

            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("更新隐患记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }

}
