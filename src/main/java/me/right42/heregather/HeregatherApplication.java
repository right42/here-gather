package me.right42.heregather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HeregatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeregatherApplication.class, args);
	}

}
