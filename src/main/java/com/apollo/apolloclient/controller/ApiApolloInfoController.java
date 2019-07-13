package com.apollo.apolloclient.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.core.enums.ConfigFileFormat;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * apollo demo 获取信息
 */
@RestController
@RequestMapping("/apollo")
public class ApiApolloInfoController {

    @RequestMapping("/api")
    public void apolloInfo(){
        /**
         * 获取默认的apollo namespace
         */
        Config config1 = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey1 = "name";
        String someDefaultValue1 = "name";
        String value1 = config1.getProperty(someKey1, someDefaultValue1);
        System.out.println("api获取到默认的apollo的值： "+value1);


        /**
         *获取公共的apollo namespace的值
         */
        String somePublicNamespace = "pwade1";
        Config config2 = ConfigService.getConfig(somePublicNamespace); //config instance is singleton for each namespace and is never null
        String someKey2 = "name1";
        String someDefaultValue2 = "name1";
        String value2 = config2.getProperty(someKey2, someDefaultValue2);
        System.out.println("api获取到公共的apollo的值： "+value2);

        /**
         * 获取yml格式的apollo namespace值
         */
        Config config3 = ConfigService.getConfig("pwade.yml");
        String someKey3 = "server.port";
        String someDefaultValue3 = "none";
        String value3 = config3.getProperty(someKey3, someDefaultValue3);
        System.out.println("api获取yml属性的apollo的值： "+value3);

        /**
         * 获取非 yml格式的apollo namespace值
         */
        String someNamespace = "test";
        ConfigFile configFile = ConfigService.getConfigFile("test", ConfigFileFormat.XML);
        String content = configFile.getContent();


        /**
         * 监听事件，监听apollo配置修改 修改不同的config(1、2、3)实现监听不同的事件
         *
         */
       config3.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                System.out.println("Changes for namespace " + changeEvent.getNamespace());
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println("***************api apollo配置监听默认事件触发***************");
                    System.out.println(String.format("apollo配置 change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));

                }
            }
        });

    }
}
