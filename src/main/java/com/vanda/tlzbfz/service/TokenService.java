package com.vanda.tlzbfz.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String gettoken(String user_name);
}
