package com.example.gmdbProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GmdbProjectApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder appBuilder)
	{
		return appBuilder.sources(GmdbProjectApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GmdbProjectApplication.class, args);
	}

}
