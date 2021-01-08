package com.vanda.tlzbfz.service;

import com.vanda.tlzbfz.entity.LgnUserInfo;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    LgnUserInfo gettoken(String user_name);
}
