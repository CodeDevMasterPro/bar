package cn.ybzy.bar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.ybzy.bar.mapper")
@SpringBootApplication
public class BarApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BarApplication.class, args);
		System.out.println("Bar = " + "Bar");
	}
	
}
