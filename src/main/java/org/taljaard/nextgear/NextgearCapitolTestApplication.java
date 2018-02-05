package org.taljaard.nextgear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.taljaard.nextgear.mybatis")
public class NextgearCapitolTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextgearCapitolTestApplication.class, args);
	}
}
