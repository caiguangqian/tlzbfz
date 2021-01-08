package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.ListUtils;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.mapper.VDbrwMapper;
import com.vanda.tlzbfz.service.TBjcjsService;
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
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Api(value = "待办任务接口")
@RestController
@RequestMapping("/tdbrw")
public class TDbrwController {


    private final static Logger log = LoggerFactory.getLogger(TRwlrController.class);

    @Autowired
    VDbrwService vDbrwService;
    @Autowired
    GsonUtil gsonUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TBjcjsService bjcjsService;
    @Autowired
    private VDbrwMapper vDbrwMapper;

    //查询列表  条件查询 都使用该接口
    @ApiOperation(value = "组合条件查询自己岗位的待办任务", httpMethod = "GET")
    @GetMapping("/dbrws")
    public ResultMsg getDbrwByCondition(Dbrw vDbrw,@RequestHeader("accept_token") String accept_token) throws IOException, ClassNotFoundException {
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        /*if("系统管理员岗位".equals(user.getGw())&&2==unit[0].length()){
            List<VDbrw> list1 = vDbrwService.queryDbrwByCondition(vDbrw);

            List<VDbrw> list = ListUtils.deepCopy(list1);
            for(int i=0;i<list.size();i++){
                TBjcjs bjcjs1 = bjcjsService.selectBjcjs(list.get(i).getBjcjs());
                list.get(i).setBjcjs(bjcjs1.getJsmc());
                TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
                list.get(i).setFbdw(fbdw.getJsmc());
            }
            if(list1==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",list);
        }*/
        Dbrw vDbrw1 = new Dbrw();
        BeanUtils.copyProperties(vDbrw,vDbrw1);
        vDbrw1.setGwdm(user.getPost());
        vDbrw1.setBjcjs(bjcjs.getJsdm());
        vDbrw1.setZt("0");
        List<VDbrw> vDbrws = vDbrwService.queryDbrwByCondition(vDbrw1);
        //list深度拷贝
        List<VDbrw> list = ListUtils.deepCopy(vDbrws);
        for(int i=0;i<list.size();i++){
            list.get(i).setBjcjs(bjcjs.getJsmc());
            TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
            list.get(i).setFbdw(fbdw.getJsmc());
        }
        if(vDbrws==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",list);

    }

    //查询列表  条件查询 都使用该接口
    @ApiOperation(value = "组合条件查询待办任务池", httpMethod = "GET")
    @GetMapping("/dbrwcs")
    public ResultMsg getDbrwByCondition1(Dbrw vDbrw,@RequestHeader("accept_token") String accept_token) throws IOException, ClassNotFoundException {
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
        if("系统管理员岗位".equals(user.getGw())&&2==unit[0].length()){
            List<VDbrw> list1 = vDbrwService.queryDbrwByCondition(vDbrw);
            List<VDbrw> list = ListUtils.deepCopy(list1);
            for(int i=0;i<list.size();i++){
                TBjcjs bjcjs1 = bjcjsService.selectBjcjs(list.get(i).getBjcjs());
                list.get(i).setBjcjs(bjcjs1.getJsmc());
                TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
                list.get(i).setFbdw(fbdw.getJsmc());
            }
            if(list1==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",list);
        }

        Dbrw dbrw = new Dbrw();
        BeanUtils.copyProperties(vDbrw,dbrw);
        if("1".equals(vDbrw.getYwcrw())){
            dbrw.setZt("1");
            dbrw.setFbdw(bjcjs.getJsdm());
            dbrw.setFbgw(user.getPost());
            List<VDbrw> vDbrws = vDbrwService.queryDbrwByCondition(dbrw);
            List<VDbrw> list = ListUtils.deepCopy(vDbrws);
            for(int i=0;i<list.size();i++){
                list.get(i).setBjcjs(bjcjs.getJsmc());
                TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
                list.get(i).setFbdw(fbdw.getJsmc());
            }
            if(vDbrws==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",list);
        }else {
            dbrw.setFbdw(bjcjs.getJsdm());
            dbrw.setGwdm(user.getPost());
            dbrw.setBjcjs(bjcjs.getJsdm());
            dbrw.setFbgw(user.getPost());
            List<VDbrw> vDbrws = vDbrwService.queryDbrwByConditionG(dbrw);
            List<VDbrw> list = ListUtils.deepCopy(vDbrws);
            for(int i=0;i<list.size();i++){
                list.get(i).setBjcjs(bjcjs.getJsmc());
                TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
                list.get(i).setFbdw(fbdw.getJsmc());
            }
            if(vDbrws==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",list);
        }


        /*Example example = new Example(VDbrw.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bjcjs",bjcjs.getJsdm());
        criteria.orEqualTo("fbdw",bjcjs.getJsdm());
        criteria.andEqualTo("id",vDbrw.getId());
        criteria.andEqualTo("gwdm",vDbrw.getGwdm());
        criteria.andEqualTo("rwlx",vDbrw.getRwlx());
        criteria.andEqualTo("zt",vDbrw.getZt());

        List<VDbrw> vDbrws = vDbrwMapper.selectByExample(example);*/
        //list深度拷贝


    }


    @ApiOperation(value = "根据岗位和被检查监所查询待办任务记录数", httpMethod = "GET")
    @GetMapping("/dbrwCount")
    public ResultMsg selectCountByGW(@RequestHeader("accept_token") String accept_token){
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);

        CountBean countBean = new CountBean();
        countBean.setGw(user.getPost());
        countBean.setBjcjs(bjcjs.getJsdm());
        countBean.setZt("0");
        long vDbrws = vDbrwService.selectCountByGW(countBean);
        return new ResultMsg("200","查询数据成功,记录数为：",vDbrws);

    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "下级确认完成待办任务,传任务id", httpMethod = "POST")
    @PostMapping("/dbrw")
    public ResultMsg updateDbrw(@RequestBody String id, @RequestHeader("accept_token") String accept_token){
        try {
            //从缓存中获取用户信息
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            ResultMsg rMsg=new ResultMsg();
            //通过id查询该条待办任务
            Gson gson = gsonUtil.createGson();
            TDh record = gson.fromJson(id,TDh.class);
            TDbrw dbrwByRwbh = vDbrwService.getDbrwById(record.getId());
            if(dbrwByRwbh!=null){
                if(dbrwByRwbh.getZt().equals("1")){
                    rMsg.setCode("400");
                    rMsg.setMessage("该待办已完成！");
                    return rMsg;
                }
                TDbrw dbrwBean = new TDbrw();
                BeanUtils.copyProperties(dbrwByRwbh,dbrwBean);
                //设置更新确认完成任务的人
                dbrwByRwbh.setXm(user.getName());
                if(dbrwByRwbh.getSycs()>0){
                    dbrwByRwbh.setWccs(dbrwByRwbh.getWccs()+1);
                    dbrwByRwbh.setSycs(dbrwByRwbh.getSycs()-1);
                }
                if(0==dbrwByRwbh.getSycs()) {
                    dbrwByRwbh.setZt("1");
                }
                vDbrwService.updateDbrw(dbrwByRwbh);
                rMsg.setCode("200");
                rMsg.setMessage("待办任务更新成功");
            }else {
                rMsg.setCode("201");
                rMsg.setMessage("待办任务更新失败");
            }
            return rMsg;
        }catch(Exception e){
            e.printStackTrace();
            log.error(String.format("确认完成待办任务记录失败, 原因:%s %n %s", e.getMessage(), Arrays.toString(e.getStackTrace())));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ResultMsg rMsg=new ResultMsg();
            rMsg.setCode("500");
            rMsg.setMessage("服务器异常！");
            return rMsg;
        }
    }
}
