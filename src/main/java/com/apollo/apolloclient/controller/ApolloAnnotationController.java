package com.apollo.apolloclient.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/apollo")
@RestController
public class ApolloAnnotationController {
    @ApolloConfig
    private Config config; //inject config for namespace application

    @ApolloConfig("pwade1")
    private Config anotherConfig; //inject config for namespace application

    @ApolloConfig("pwade.yml")
    private Config ymlConfig; //inject config for namespace application.yml



    @RequestMapping("/annota")
    public void apolloInfo(){
        System.out.println("注解获取到apollo的值默认： "+geteDfaultDNameSpace()+" 公共： "+getePublicNameSpace()+"yml私有："+getYmlNameSpace());

    }

    @ApolloConfigChangeListener("application")
    private void someOnChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("name")) {
            String name = config.getProperty("name","name");
            System.out.println("注解pollo监测到默认信息已经修改"+name);
        }
    }

    @ApolloConfigChangeListener("pwade1")
    private void someOnPublicChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("name1")) {
            String name1 = anotherConfig.getProperty("name1","name1");
            System.out.println("注解pollo监测到公共信息已经修改"+name1);
        }
    }

    @ApolloConfigChangeListener("pwade.yml")
    private void someOnYmlChange(ConfigChangeEvent changeEvent) {
        //update injected value of batch if it is changed in Apollo
        if (changeEvent.isChanged("server.port")) {
            String name3 = ymlConfig.getProperty("server.port","server.port");
            System.out.println("注解pollo监测到公共信息已经修改"+name3);
        }
    }

    /**
     * 根据config来获取值
     * @return
     */
    public String geteDfaultDNameSpace () {
        return  config.getProperty("name", "none");
    }

    public String getePublicNameSpace () {
        return  anotherConfig.getProperty("name1", "none");
    }


    public String getYmlNameSpace() {
        return  ymlConfig.getProperty("server.port", "none");
    }


}
