package com.vanda.tlzbfz.controller;


import com.vanda.tlzbfz.service.TDbrwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    TDbrwService tDbrwService;
    @RequestMapping("/test")
    public void test(){

        System.out.println("@@"+tDbrwService.selectAll());
    }

}
