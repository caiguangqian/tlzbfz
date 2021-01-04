package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.service.TBjcjsService;
import com.vanda.tlzbfz.service.TRwlrService;
import com.vanda.tlzbfz.service.VDbrwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(value = "任务录入接口")
@RestController
@RequestMapping("/trwlr")
public class TRwlrController {

    @Autowired
    private TRwlrService tRwlrService;
    @Autowired
    private VDbrwService vDbrwService;
    @Autowired
    private GsonUtil gsonUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TBjcjsService bjcjsService;

    private static final Logger log = LoggerFactory.getLogger(TRwlrController.class);


    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "插入任务录入接口", httpMethod = "POST")
    @PostMapping("/rwlr")
    public ResultMsg insertRwlr(@RequestBody String json,@RequestHeader("accept_token") String accept_token) throws Exception {
        try{
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String[] unit = user.getUnitCode();
            TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
            Gson gson = gsonUtil.createGson();
            RwlrExtendBean record = gson.fromJson(json,RwlrExtendBean.class);
            //获取前端传过来的代办任务
            List<TDbrw> list = record.getTdbrwBeans();

            //获取前端传过来的录入bean类
            TRwlrBean rwlrBean = new TRwlrBean();
            BeanUtils.copyProperties(record,rwlrBean);

            ResultMsg resultMsg=new ResultMsg();
            String id = String.valueOf(System.currentTimeMillis());
            rwlrBean.setRwbh(id.substring(6,id.length()));
            rwlrBean.setFbdw(bjcjs.getJsdm());
            int result = tRwlrService.insertSelective(rwlrBean);

            if(list.size()>0){
                for (int i=0;i<list.size();i++){
                    TDbrw dbrwBean = new TDbrw();
                    dbrwBean.setId(String.valueOf(System.currentTimeMillis()));
                    dbrwBean.setXm(list.get(i).getXm());
                    dbrwBean.setRwbh(rwlrBean.getRwbh());
                    dbrwBean.setBjcjs(list.get(i).getBjcjs());
                    dbrwBean.setZt("0");
                    dbrwBean.setWccs((long) 0);
                    dbrwBean.setSycs(rwlrBean.getYqcs());
                    dbrwBean.setGw(list.get(i).getGw());
                    vDbrwService.inserDbrwSelective(dbrwBean);
                }
            }

            if(result<=0){
                resultMsg.setCode("400");
                resultMsg.setMessage("录入任务失败");
            }else {
                resultMsg.setCode("200");
                resultMsg.setMessage("录入任务成功");
            }

            return resultMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("录入任务失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "根据任务编号删除一条记录接口", httpMethod = "DELETE")
    @DeleteMapping("/rwlr")
    public ResultMsg selectByRwbh(@RequestParam("rwbh") String rwbh,@RequestHeader("accept_token") String accept_token){
        try {
            ResultMsg rMsg=new ResultMsg();
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String[] unit = user.getUnitCode();
            TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
            List<TRwlrBean> list = tRwlrService.getRwlrByCondition(bjcjs.getJsdm(),rwbh);
            if(list.size()==0){
                rMsg.setCode("404");
                rMsg.setMessage("删除记录不存在!");
                return rMsg;
            }

            int rwlrBean = tRwlrService.deleteByRwbh(rwbh);

            if(rwlrBean<=0){
                rMsg.setCode("400");
                rMsg.setMessage("删除记录为空");
            }else {
                List<TDbrw> dbrwBeanList = vDbrwService.selectByRwbh(rwbh);
                for (int i=0;i<dbrwBeanList.size();i++){
                    vDbrwService.deleteById(dbrwBeanList.get(i).getId());
                }
                rMsg.setCode("200");
                rMsg.setMessage("删除成功");
            }
            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("删除记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "更新录入接口", httpMethod = "PUT")
    @PutMapping("/rwlr")
    public  ResultMsg updateRwlr(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        try {
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String[] unit = user.getUnitCode();
            TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
            Gson gson = gsonUtil.createGson();
            ResultMsg rMsg=new ResultMsg();
            TRwlrBean record = gson.fromJson(json,TRwlrBean.class);
            if(bjcjs.getJsdm().equals(record.getFbdw())==false){
                rMsg.setCode("404");
                rMsg.setMessage("更新失败！只允许发布单位用户更新数据!");
                return rMsg;
            }
            int rel = tRwlrService.updateBySelective(record);
            if(rel<=0){
                rMsg.setCode("201");
                rMsg.setMessage("任务录入编辑失败");
            }else {
                rMsg.setCode("200");
                rMsg.setMessage("任务录入编辑成功");
            }
            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("更新任务失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }


    //录入列表  条件查询  都用该接口
    @ApiOperation(value = "录入查询接口，传参为空查询所有记录，传入任务编号查询一条记录", httpMethod = "GET")
    @GetMapping("/rwlrs")
    public ResultMsg queryRwlrList(String rwbh,@RequestHeader("accept_token") String accept_token){
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        String jsmc = bjcjs.getJsdm();
        List<TRwlrBean> list = tRwlrService.getRwlrByCondition(jsmc,rwbh);
        if(list==null){
            return  new ResultMsg("400","录入任务查询为空",null);
        }
        return new ResultMsg("200","任务查询成功",list);
    }

}
