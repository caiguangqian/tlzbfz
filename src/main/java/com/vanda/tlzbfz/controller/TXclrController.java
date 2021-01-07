package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.*;
import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.service.TBjcjsService;
import com.vanda.tlzbfz.service.TDbyhService;
import com.vanda.tlzbfz.service.TXclrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 巡查录入 前端控制器
 * </p>
 *
 * @author onion
 * @since 2020-12-17
 */
@Api(value = "巡查录入接口")
@RestController
@RequestMapping("/txclr")
public class TXclrController {

    @Autowired
    private GsonUtil gsonUtil;
    @Autowired
    private TXclrService xclrService;
    @Autowired
    private TDbyhService dbyhService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TBjcjsService bjcjsService;
    private static final Logger log = LoggerFactory.getLogger(TXclrController.class);


    @ApiOperation(value = "插入巡查录入接口", httpMethod = "POST")
    @PostMapping("/xclr")
    @Transactional(rollbackFor = Exception.class)
    public ResultMsg insertXclr(@RequestBody String json, @RequestHeader("accept_token") String accept_token) throws Exception {
        try {
                SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
                String[] unit = user.getUnitCode();


                Gson gson = gsonUtil.createGson();
                TXclrExtenBean record = gson.fromJson(json,TXclrExtenBean.class);
                //获取前端传过来的代办隐患
                List<TDbyh> list = record.getTDbyhs();

                //获取前端传过来的录入bean类
                TXclr xclr = new TXclr();
                BeanUtils.copyProperties(record,xclr);
                ResultMsg resultMsg=new ResultMsg();
                TBjcjs jcjs = bjcjsService.selectBjcjs(unit[0]);
                xclr.setJcjs(jcjs.getJsdm());
                TBjcjs bjcjs = bjcjsService.selectBjcjs(xclr.getBjcjs());
                xclr.setBjcjs(bjcjs.getJsdm());
                String id = String.valueOf(System.currentTimeMillis());
                xclr.setXcbh(id);
                int result = xclrService.insertSelective(xclr);

                for (int i=0;i<list.size();i++){
                    TDbyh dbyh = new TDbyh();
                    dbyh.setYhbh(String.valueOf(System.currentTimeMillis()+RandomStringUtils.random(2)));
                    dbyh.setYhlx(list.get(i).getYhlx());
                    dbyh.setYhlb(list.get(i).getYhlb());
                    dbyh.setYhxm(list.get(i).getYhxm());
                    dbyh.setYhjbqk(list.get(i).getYhjbqk());
                    dbyh.setQy(list.get(i).getQy());
                    dbyh.setFs(list.get(i).getFs());
                    dbyh.setYhzt(list.get(i).getYhzt());
                    dbyh.setXcbh(xclr.getXcbh());
                    dbyhService.insertDnyh(dbyh);
                }

                if(result<=0){
                    resultMsg.setCode("400");
                    resultMsg.setMessage("录入巡查记录为空");
                }else {
                    resultMsg.setCode("200");
                    resultMsg.setMessage("录入巡查记录成功");
                }
                return resultMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("录入巡查记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }

    @ApiOperation(value = "根据待办巡查编号删除一条记录接口", httpMethod = "DELETE")
    @DeleteMapping("/xclr")
    @Transactional(rollbackFor = Exception.class)
    public ResultMsg deleteByxcbh(@RequestParam("xcbh") String xcbh,@RequestHeader("accept_token") String accept_token){
        try{
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String[] unit = user.getUnitCode();
            TXclr xclr = new TXclr();
            xclr.setXcbh(xcbh);
            ResultMsg rMsg=new ResultMsg();
            List<TXclr> list = xclrService.selectAllByJcjs(xclr);
            if(unit[0].equals(list.get(0).getJcjs())==false){
                rMsg.setCode("404");
                rMsg.setMessage("没有权限删除记录！");
                return rMsg;
            }

            //先删除关联的待办隐患信息，再执行删除待办巡查操作
            VDbyh dbyh = new VDbyh();
            dbyh.setXcbh(xcbh);
            List<VDbyh> dbyhs = dbyhService.selectDbyhByCondition(dbyh);
            for (int i=0;i<dbyhs.size();i++){
                dbyhService.deleteBydbyh(dbyhs.get(i).getYid());
            }
            int xclrBean = xclrService.deleteByXcbh(xcbh);

            if(xclrBean<=0){
                rMsg.setCode("400");
                rMsg.setMessage("删除巡查记录为空");
            }else {
                rMsg.setCode("200");
                rMsg.setMessage("删除成功");
            }
            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("删除信息失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage(e.getMessage());
            return rMsg;
        }
    }

    @ApiOperation(value = "确认完成巡查更新录入状态接口", httpMethod = "PUT")
    @PutMapping("/xclr")
    @Transactional(rollbackFor = Exception.class)
    public  ResultMsg updateByXcbh(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        try{
            ResultMsg rMsg=new ResultMsg();
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            String[] unit = user.getUnitCode();
            //TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
            Gson gson = gsonUtil.createGson();
            TXclr record = gson.fromJson(json,TXclr.class);
            TBjcjs bjcjs = bjcjsService.selectBjcjs(record.getJcjs());
            if(unit[0].equals(bjcjs.getJsdm())==false){
                rMsg.setCode("404");
                rMsg.setMessage("没有权限确认完成!");
                return rMsg;
            }
            TXclr xclr = new TXclr();
            BeanUtils.copyProperties(record,xclr);
            xclr.setZgzt("1");
            xclr.setFkrq(new Date());
            //更新巡查录入的巡查状态
            int rel = xclrService.updateByXcbh(xclr);

            TDbyh dbyh1 = new TDbyh();
            dbyh1.setXcbh(xclr.getXcbh());
            //根据巡查编号查询出所有代办隐患
            List<TDbyh> list =dbyhService.selectDbyhByExample(dbyh1);
            //更新代办所有隐患状态，因为巡查状态完成后需要同步更新代办隐患状态
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setYhzt("1");
                dbyhService.updateDbyh(list.get(i));
            }

            if(rel<=0){
                rMsg.setCode("201");
                rMsg.setMessage("巡查信息更新失败");
            }else {
                rMsg.setCode("200");
                rMsg.setMessage("巡查信息更新成功");
            }

            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("巡查信息更新失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }

    }

    @ApiOperation(value = "查询所有巡查录入记录", httpMethod = "GET")
    @GetMapping("/xclrs")
    public ResultMsg queryXclrList(@RequestHeader("accept_token") String accept_token) throws Exception {
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        String fbdw = bjcjs.getJsmc();
        List<TXclr> list = xclrService.queryXclrList(bjcjs);
        if(list==null){
            return  new ResultMsg("400","录入任务查询为空",null);
        }
        List<TXclr> tXclrs = ListUtils.deepCopy(list);
        for (int i = 0; i < tXclrs.size(); i++) {
            TBjcjs bjcdw =bjcjsService.selectBjcjs(list.get(i).getBjcjs());
            tXclrs.get(i).setJcjs(fbdw);
            tXclrs.get(i).setBjcjs(bjcdw.getJsmc());
        }
        return new ResultMsg("200","巡查记录查询成功",tXclrs);
    }

}
