package com.puxinxiaolin.study.service.impl;

import com.puxinxiaolin.study.service.MyService;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    public void performTask() {
        System.out.println("perform task");
    }

}
