package com.qcl.pattern.controller;

import com.qcl.pattern.service.IServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 2020/9/8
 */
@RestController
public class TestController {

    @Autowired
    private IServiceTest serviceTest;

    @RequestMapping("/test")
    public String method(){
        System.out.println("hello");

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(()->{
                //TODO
                serviceTest.method();
            });
            thread.start();
        }
        return null;
    }
}
