package com.vanda.tlzbfz.controller;


import com.google.gson.Gson;
import com.vanda.tlzbfz.common.util.GsonUtil;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.common.util.ResultMsg;
import com.vanda.tlzbfz.entity.RwlrExtendBean;
import com.vanda.tlzbfz.entity.SystemLoginUser;
import com.vanda.tlzbfz.entity.TDbrwBean;
import com.vanda.tlzbfz.entity.TRwlrBean;
import com.vanda.tlzbfz.service.TRwlrService;
import com.vanda.tlzbfz.service.VDbrwService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value = "任务录入接口")
@RestController
@RequestMapping("/rwlr")
public class TRwlrController {

    @Autowired
    private TRwlrService tRwlrService;

    @Autowired
    private VDbrwService vDbrwService;

    @Autowired
    private GsonUtil gsonUtil;

    @Autowired
    private RedisUtil redisUtil;

   // @Transactional
    @ApiOperation(value = "插入任务录入接口", httpMethod = "POST")
    @PostMapping("/insertRwlr")
    public ResultMsg insertRwlr(@RequestBody String json,@RequestHeader("accept_token") String accept_token) throws Exception {

        Gson gson = gsonUtil.createGson();
        RwlrExtendBean record = gson.fromJson(json,RwlrExtendBean.class);
        //获取前端传过来的代办任务
        List<TDbrwBean> list = record.getTdbrwBeans();

        //获取前端传过来的录入bean类
        TRwlrBean rwlrBean = new TRwlrBean();
        BeanUtils.copyProperties(record,rwlrBean);

        ResultMsg resultMsg=new ResultMsg();
        String id = String.valueOf(System.currentTimeMillis());
        rwlrBean.setRwbh(id.substring(6,id.length()));
       // System.out.println("@@@@"+id.substring(6,id.length()));
        int result = tRwlrService.insertSelective(rwlrBean);

        for (int i=0;i<list.size();i++){
            TDbrwBean dbrwBean = new TDbrwBean();
            dbrwBean.setId(String.valueOf(System.currentTimeMillis()));
            dbrwBean.setRwbh(rwlrBean.getRwbh());
            dbrwBean.setBjcjs(list.get(i).getBjcjs());
            dbrwBean.setZt("0");
            dbrwBean.setWccs((long) 0);
            dbrwBean.setSycs(rwlrBean.getYqcs());
            dbrwBean.setGw(list.get(i).getGw());
            vDbrwService.inserDbrwSelective(dbrwBean);
        }
            //SystemLoginUser user = (SystemLoginUser) redisUtil.get(accept_token);
            if(result<=0){
                resultMsg.setCode("400");
                resultMsg.setMessage("录入任务为空");
            }else {
                resultMsg.setCode("200");
            resultMsg.setMessage("录入任务成功");
            //resultMsg.setDate(record);
        }

        return resultMsg;
    }

    //@Transactional
    @ApiOperation(value = "根据用户编号删除一条记录接口", httpMethod = "DELETE")
    @DeleteMapping("/deleteByrwbh")
    public ResultMsg selectByRwbh(@RequestParam("rwbh") String rwbh){
        int rwlrBean = tRwlrService.deleteByRwbh(rwbh);
        ResultMsg rMsg=new ResultMsg();
        if(rwlrBean<=0){
            rMsg.setCode("400");
            rMsg.setMessage("删除记录为空");
        }else {
            rMsg.setCode("200");
            rMsg.setMessage("删除成功");
        }
        return rMsg;
    }

   // @Transactional
    @ApiOperation(value = "更新录入接口", httpMethod = "POST")
    @PostMapping("/updateRwlr")
    public  ResultMsg updateRwlr(@RequestBody String json,@RequestHeader("accept_token") String accept_token){
        Gson gson = gsonUtil.createGson();
        TRwlrBean record = gson.fromJson(json,TRwlrBean.class);
        ResultMsg rMsg=new ResultMsg();
        int rel = tRwlrService.updateBySelective(record);
        if(rel<=0){
            rMsg.setCode("201");
            rMsg.setMessage("任务编辑失败");
        }else {
           /* TDbrwBean dbrwByRwbh = vDbrwService.getDbrwById(record.getRwbh());
            if(dbrwByRwbh!=null){
                dbrwByRwbh.setSycs(dbrwByRwbh.getSycs()+1);//每次录入编辑时要求次数只能递增1条,相应的待办任务剩余次数也递增1条
                vDbrwService.updateDbrw(dbrwByRwbh);
                rMsg.setCode("200");
                rMsg.setMessage("任务编辑成功");
                rMsg.setDate(record);

            }else {
                rMsg.setCode("202");
                rMsg.setMessage("待办任务条数同步失败");
            }*/
            rMsg.setCode("200");
            rMsg.setMessage("任务编辑成功");
            rMsg.setDate(record);
        }

        return rMsg;
    }


    //录入列表  条件查询  都用该接口
    @ApiOperation(value = "录入查询接口，传参为空查询所有记录，传入任务编号查询一条记录", httpMethod = "GET")
    @GetMapping("/queryRwlrList")
    public ResultMsg queryRwlrList(String rwbh){
        List<TRwlrBean> list = tRwlrService.getRwlrByCondition(rwbh);
        if(list==null){
            return  new ResultMsg("400","录入任务查询为空",null);
        }
        return new ResultMsg("200","任务查询成功",list);
    }

}
