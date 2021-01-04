package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.ListUtils;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.*;
import com.vanda.tlzbfz.service.TBjcjsService;
import com.vanda.tlzbfz.service.VDbrwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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

    //查询列表  条件查询 都使用该接口
    @ApiOperation(value = "组合条件查询自己岗位的待办任务,cxlx为0默认查询自己岗位，其他根据条件查询所有", httpMethod = "GET")
    @GetMapping("/dbrws")
    public ResultMsg getDbrwByCondition(Dbrw vDbrw,@RequestHeader("accept_token") String accept_token) throws IOException, ClassNotFoundException {
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        if("0".equals(vDbrw.getCxlx())){
            vDbrw.setGw(user.getPost());
            String[] unit = user.getUnitCode();
            TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);
            //狗日的换行符
            String jsmc = bjcjs.getJsmc();
            jsmc=jsmc.replaceAll("\n","");
            vDbrw.setBjcjs(bjcjs.getJsdm());
            //System.out.println("@@@"+vDbrw.getGwmc()+vDbrw.getBjcjs());

            List<VDbrw> vDbrws = vDbrwService.queryDbrwByCondition(vDbrw);
            //System.out.println("@@"+vDbrws);
            //list深度拷贝
            List<VDbrw> list = ListUtils.deepCopy(vDbrws);
            for(int i=0;i<list.size();i++){
                list.get(i).setBjcjs(bjcjs.getJsmc());
                TBjcjs fbdw = bjcjsService.selectBjcjs(list.get(i).getFbdw());
                list.get(i).setFbdw(fbdw.getJsmc());
            }
            //System.out.println("@@@"+list);
            if(vDbrws==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",list);
        }else{
            List<VDbrw> vDbrws1 = vDbrwService.queryDbrwByCondition(vDbrw);
            if(vDbrws1==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",vDbrws1);
        }

    }


    @ApiOperation(value = "根据岗位和被检查监所查询待办任务记录数", httpMethod = "GET")
    @GetMapping("/dbrwCount")
    public ResultMsg selectCountByGW(@RequestHeader("accept_token") String accept_token){
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
        String[] unit = user.getUnitCode();
        TBjcjs bjcjs = bjcjsService.selectBjcjs(unit[0]);

        //狗日的换行符
        String jsmc = bjcjs.getJsmc();
        jsmc=jsmc.replaceAll("\n","");

        CountBean countBean = new CountBean();
        countBean.setGw(user.getPost());
        countBean.setBjcjs(bjcjs.getJsdm());
        long vDbrws = vDbrwService.selectCountByGW(countBean);
        return new ResultMsg("200","查询数据成功,记录数为：",vDbrws);

    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "下级确认完成待办任务", httpMethod = "PUT")
    @PutMapping("/dbrw")
    public ResultMsg updateDbrw(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        try {
            Gson gson = gsonUtil.createGson();
            TDbrw tDbrwBean = gson.fromJson(json , TDbrw.class);
            //从缓存中获取用户信息
            SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);

            ResultMsg rMsg=new ResultMsg();
            //通过id查询该条待办任务
            TDbrw dbrwByRwbh = vDbrwService.getDbrwById(tDbrwBean.getId());
            if(dbrwByRwbh!=null){
                TDbrw dbrwBean = new TDbrw();
                //设置更新确认完成任务的人
                dbrwByRwbh.setXm(user.getName());
                dbrwByRwbh.setWccs(dbrwByRwbh.getWccs()+1);
                dbrwByRwbh.setSycs(dbrwByRwbh.getSycs()-1);
                //System.out.println("@@"+dbrwByRwbh.getSycs());
                if(dbrwByRwbh.getSycs()==0){
                    dbrwByRwbh.setZt("1");
                }
                vDbrwService.updateDbrw(dbrwByRwbh);
                rMsg.setCode("200");
                rMsg.setMessage("待办任务更新成功");
                //rMsg.setData(dbrwByRwbh);
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
