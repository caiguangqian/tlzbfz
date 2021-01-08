package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.common.util.DateUtils;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.entity.LgnUserInfo;
import com.vanda.tlzbfz.entity.Lgn_user;
import com.vanda.tlzbfz.entity.SystemLoginUser;
import com.vanda.tlzbfz.entity.TUserGw;
import com.vanda.tlzbfz.mapper.TGwMapper;
import com.vanda.tlzbfz.mapper.TUserGwMapper;
import com.vanda.tlzbfz.mapper.TokenMapper;
import com.vanda.tlzbfz.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenDao;
    @Autowired
    private TGwMapper gwMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TUserGwMapper userGwMapper;


    @Override
    public LgnUserInfo gettoken(String pki_no) {
        /*Example example = new Example(TUserGw.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pk_no",pki_no);
        List<TUserGw> tUserGws = userGwMapper.selectByExample(example);
        TUserGw userGw = tUserGws.get(0);*/
        LgnUserInfo userInfo = new LgnUserInfo();
        Lgn_user userinfo=tokenDao.selectuser(pki_no);
        userInfo.setUsername(userinfo.getReal_name());
        SystemLoginUser user = new SystemLoginUser();
        user.setName(userinfo.getReal_name());
        user.setUnitCode(userinfo.getUnit().split("\\|"));
        user.setCardNumber(userinfo.getPki_no());
        user.setGw(userinfo.getGw());
        Example example1 = new Example(TUserGw.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("pk_no",pki_no);
        List<TUserGw> list = userGwMapper.selectByExample(example1);

        if (list.size()<=0){
            String rediskey="_"+DateUtils.getDateRandom()+"_"+user.getUnitCode()[0];
            if(redisUtil.set(rediskey,user)){
                redisUtil.expire(rediskey,86400);
                userInfo.setToken(rediskey);
                return userInfo;
            }
        }
        user.setPost(list.get(0).getGwdm());
        String rediskey=DateUtils.getDateRandom()+"_"+user.getUnitCode()[0];


        if(redisUtil.set(rediskey,user)){
            redisUtil.expire(rediskey,86400);
            userInfo.setToken(rediskey);
            userInfo.setGw(user.getPost());
            return userInfo;
        }
        return userInfo;
    }

}
