package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "待办任务接口")
@RestController
@RequestMapping("/dbrw")
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
    @GetMapping("/getDbrwByCondition")
    public ResultMsg getDbrwByCondition(Dbrw vDbrw,@RequestHeader("accept_token") String accept_token){
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
            if(vDbrws==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",vDbrws);
        }else{
            List<VDbrw> vDbrws1 = vDbrwService.queryDbrwByCondition(vDbrw);
            if(vDbrws1==null){
                return  new ResultMsg("400","查询数据为空",null);
            }
            return new ResultMsg("200","查询数据成功",vDbrws1);
        }

    }


    @ApiOperation(value = "根据岗位和被检查监所查询待办任务记录数", httpMethod = "GET")
    @GetMapping("/selectCountByGwAndBjcjs")
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


    // @Transactional
    @ApiOperation(value = "下级确认完成待办任务", httpMethod = "POST")
    @PostMapping("/updateDbrw")
    public ResultMsg updateDbrw(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        Gson gson = gsonUtil.createGson();
        TDbrwBean tDbrwBean = gson.fromJson(json , TDbrwBean.class);
        //从缓存中获取用户信息
        SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);

        ResultMsg rMsg=new ResultMsg();
        //通过id查询该条待办任务
        TDbrwBean dbrwByRwbh = vDbrwService.getDbrwById(tDbrwBean.getId());
        if(dbrwByRwbh!=null){
            TDbrwBean dbrwBean = new TDbrwBean();
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
            rMsg.setDate(dbrwByRwbh);
        }else {
            rMsg.setCode("201");
            rMsg.setMessage("待办任务更新失败");
        }
        return rMsg;
    }
}
