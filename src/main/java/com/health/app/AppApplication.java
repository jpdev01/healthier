package com.health.app;

import com.health.app.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
        System.out.println( new JwtUtil().generateToken("admin"));
		SpringApplication.run(AppApplication.class, args);
	}

}
