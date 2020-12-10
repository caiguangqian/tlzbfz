package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.service.TRwlrService;
import com.vanda.tlzbfz.service.VDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rwlr")
public class TRwlrController {

    @Autowired
    private TRwlrService tRwlrService;

    @Autowired
    private VDbrwService vDbrwService;


    @PostMapping("/insertRwlr")
    public ResultMsg insertRwlr(TRwlrBean record){

         ResultMsg resultMsg=new ResultMsg();
         int result = tRwlrService.insertSelective(record);

         if(result<=0){
             resultMsg.setCode("400");
             resultMsg.setMessage("录入任务为空");
         }else {
             resultMsg.setCode("200");
             resultMsg.setMessage("录入任务成功");
             resultMsg.setDate(record);
         }

     return resultMsg;
 }


     @PostMapping("/updateRwlr")
     public  ResultMsg updateRwlr(TRwlrBean record){

         ResultMsg rMsg=new ResultMsg();
            int rel = tRwlrService.updateBySelective(record);
            if(rel<=0){
                rMsg.setCode("201");
                rMsg.setMessage("任务编辑失败");
            }else {
                TDbrwBean dbrwByRwbh = vDbrwService.getDbrwByRwbh(record.getRwbh());
                if(dbrwByRwbh!=null){
                    dbrwByRwbh.setSycs(dbrwByRwbh.getSycs()+1);//每次录入编辑时要求次数只能递增1条,相应的待办任务剩余次数也递增1条
                    vDbrwService.updateDbrw(dbrwByRwbh);
                    rMsg.setCode("200");
                    rMsg.setMessage("任务编辑成功");
                    rMsg.setDate(record);

                }else {
                    rMsg.setCode("202");
                    rMsg.setMessage("待办任务条数同步失败");
                }
            }

            return rMsg;
    }


    //录入列表  条件查询  都用该接口
    @GetMapping("/queryRwlrList")
    public ResultMsg queryRwlrList(TRwlrBean tRwlrBean){

        List<TRwlrBean> tRwlrBeans = tRwlrService.getRwlrByCondition(tRwlrBean);
        if(tRwlrBeans==null){
            return  new ResultMsg("400","录入任务查询为空",null);
        }
        return new ResultMsg("200","任务查询成功",tRwlrBeans);
    }

}
