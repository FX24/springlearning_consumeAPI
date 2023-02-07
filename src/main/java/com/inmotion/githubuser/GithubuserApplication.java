package com.inmotion.githubuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GithubuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubuserApplication.class, args);
	}

}
