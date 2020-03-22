package com.github.gustavovitor.photos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PhotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotosApplication.class, args);
	}

}
