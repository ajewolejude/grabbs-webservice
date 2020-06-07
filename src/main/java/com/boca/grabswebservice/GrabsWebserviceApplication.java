package com.boca.grabswebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GrabsWebserviceApplication extends SpringBootServletInitializer {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(GrabsWebserviceApplication.class);
	}

	public static void main(String[] args){
		SpringApplication.run(GrabsWebserviceApplication.class, args);
	}

}
