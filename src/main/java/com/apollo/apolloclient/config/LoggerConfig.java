package com.apollo.apolloclient.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;


/***
 * spring boot 集成apollo配置实现自动监听功能
 *
 * @ApolloConfig注解：将Apollo服务端的中的配置注入这个类中。
 * @ApolloConfigChangeListener注解：监听配置中心配置的更新事件，若该事件发生，则调用refreshLoggingLevels方法，处理该事件。
 * ConfigChangeEvent参数：可以获取被修改配置项的key集合，以及被修改配置项的新值、旧值和修改类型等信息。
 */
@Configuration
public class LoggerConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggerConfig.class);




    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void configChangeListter(ConfigChangeEvent changeEvent) {
        refreshLoggingLevels();
    }

    @PostConstruct
    private void refreshLoggingLevels() {
        Set<String> keyNames = config.getPropertyNames();
        for (String key : keyNames) {
                String strLevel = config.getProperty(key, "info");
            System.out.println("***************springboot apollo配置监听事件触发***************");
            logger.info("{}:{}", key, strLevel);
            System.out.println("***************springboot apollo配置监听事件结束***************");

        }
    }

}
