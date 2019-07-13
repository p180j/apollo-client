package com.apollo.apolloclient.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解方式得到apollo配置信息
 * @author  pengjw
 */
@RestController
@RequestMapping("/apollo")
public class BootApolloController {
   @Value("${name}")
    private String name;

    @Value("${name1}")
    private String name1;

    @Value("${server.port:6666}")
    private String adress;

    @RequestMapping("/boot")
    public void apolloInfo(){
        System.out.println("spring boot获取到apollo的值默认： "+name+" 公共： "+name1+"yml私有： "+adress);

    }
}
