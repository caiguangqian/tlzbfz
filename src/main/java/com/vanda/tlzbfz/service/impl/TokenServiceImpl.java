package com.vanda.tlzbfz.service.impl;

import com.vanda.tlzbfz.common.util.DateUtils;
import com.vanda.tlzbfz.common.util.RedisUtil;
import com.vanda.tlzbfz.entity.Lgn_user;
import com.vanda.tlzbfz.entity.SystemLoginUser;
import com.vanda.tlzbfz.entity.TGw;
import com.vanda.tlzbfz.mapper.TGwMapper;
import com.vanda.tlzbfz.mapper.TokenMapper;
import com.vanda.tlzbfz.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenDao;
    @Autowired
    private TGwMapper gwMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String gettoken(String pki_no) {
        Lgn_user userinfo=tokenDao.selectuser(pki_no);
        SystemLoginUser user = new SystemLoginUser();
        user.setName(userinfo.getReal_name());
        user.setUnitCode(userinfo.getUnit().split("\\|"));
        user.setPost(userinfo.getGw());
        user.setCardNumber(userinfo.getPki_no());
        String rediskey=DateUtils.getDateRandom()+"_"+user.getUnitCode()[0];
        if(redisUtil.set(rediskey,user)){
            redisUtil.expire(rediskey,86400);
            return rediskey;
        }
        return "1111";
    }

}
