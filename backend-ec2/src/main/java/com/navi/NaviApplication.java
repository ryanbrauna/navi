package com.navi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NaviApplication {

	public static void main(String[] args) {
		SpringApplication.run(NaviApplication.class, args);
	}

}
