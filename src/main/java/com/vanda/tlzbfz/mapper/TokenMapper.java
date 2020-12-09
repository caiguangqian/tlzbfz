package com.vanda.tlzbfz.mapper;

import com.vanda.tlzbfz.entity.Lgn_user;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenMapper {
    Lgn_user selectuser(String user_name);
}
