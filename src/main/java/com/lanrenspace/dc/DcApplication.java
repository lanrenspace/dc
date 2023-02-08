package com.lanrenspace.dc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lanrenspace.dc.mapper")
@SpringBootApplication
public class DcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcApplication.class, args);
	}

}
