package com.vanda.tlzbfz;

import com.vanda.tlzbfz.entity.TDbyh;
import com.vanda.tlzbfz.entity.TDh;
import com.vanda.tlzbfz.entity.TXclr;
import com.vanda.tlzbfz.mapper.TDbyhMapper;
import com.vanda.tlzbfz.mapper.TUserGwMapper;
import com.vanda.tlzbfz.mapper.TXclrMapper;
import com.vanda.tlzbfz.service.TDbyhService;
import com.vanda.tlzbfz.service.TDhService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
class TlzbfzApplicationTests {

    @Autowired
    private TDbyhService dbyhService;
    @Autowired
    private TDhService dhService;
    @Autowired
    private TDbyhMapper dbyhMapper;
    @Autowired
    private TXclrMapper xclrMapper;
    @Autowired
    private TUserGwMapper userGwMapper;

    @Test
    public void test(){
        TDbyh dbyh = new TDbyh();
        dbyh.setXcbh("1608518565514");
        Example example = new Example(TDbyh.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("xcbh",dbyh.getXcbh());
        List<TDbyh> list = dbyhMapper.selectByExample(example);
        for(TDbyh dbyh1 : list){
            System.out.println(dbyh1.getYhbh()+":"+dbyh1.getYhxm());
        }

    }

    @Test
    public void test01(){
        System.out.println("@@"+userGwMapper.selectAll());
    }


}
