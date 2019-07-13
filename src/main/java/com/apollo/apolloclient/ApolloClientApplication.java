package com.apollo.apolloclient;

import com.apollo.apolloclient.config.LoggerConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableApolloConfig
@ComponentScan("com.apollo.apolloclient.*")
public class ApolloClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloClientApplication.class, args);
	}

}
