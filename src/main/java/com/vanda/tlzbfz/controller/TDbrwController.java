package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.VDbrw;
import com.vanda.tlzbfz.service.VDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 待办任务 前端控制器
 * </p>
 *
 * @author onion
 * @since 2020-12-07
 */
@RestController
@RequestMapping("/dbrw")
public class TDbrwController {

    @Autowired
    VDbrwService vDbrwService;

    //查询列表  条件查询 都使用该接口
    @GetMapping("/getDbrwByCondition")
    public ResultMsg getDbrwByCondition(VDbrw vDbrw){

        List<VDbrw> vDbrws = vDbrwService.queryDbrwByCondition(vDbrw);
        if(vDbrws==null){
            return  new ResultMsg("400","查询数据为空",null);
        }
        return new ResultMsg("200","查询数据成功",vDbrws);

    }

    
    @PostMapping("/updateDbrw")
    public ResultMsg updateDbrw(TDbrwBean tDbrwBean){

        ResultMsg rMsg=new ResultMsg();
        TDbrwBean dbrwByRwbh = vDbrwService.getDbrwByRwbh(tDbrwBean.getRwbh());
        if(dbrwByRwbh!=null){

            dbrwByRwbh.setWccs(dbrwByRwbh.getWccs()+1);
            dbrwByRwbh.setSycs(dbrwByRwbh.getSycs()-1);
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
