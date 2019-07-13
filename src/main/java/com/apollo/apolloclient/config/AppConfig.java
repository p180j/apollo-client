package com.apollo.apolloclient.config;

import com.apollo.apolloclient.controller.ApolloAnnotationController;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig
public class AppConfig {
   /* @Bean
    public ApolloAnnotationController apolloAnnotationController() {
        return new ApolloAnnotationController();
    }*/
}
