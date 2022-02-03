package com.vtw.jjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//스프링 부트의 가장 기본적인 설정을 선언해 줌
public class JinhaAssignmentApplication {
	/*
	 @SpringBootApplication 안에는 다시 여러개의 어노테이션이 선언되어 있는데
	 여기서 눈 여겨 볼 설정은 @ComponentScan과 @EnableAutoConfiguration
	 @ComponentScan은 @Component 어노테이션 및 @Service, @Repository, @Controller 등의 어노테이션을 스캔하여 Bean으로 등록해주는 어노테이션
	 @EnableAutoConfiguration은 사전에 정의한 라이브러리들을  Bean으로 등록해 주는 어노테이션
	  사전에 정의한 라이브러리들 모두가 등록되는 것은 아니고 특정 Condition(조건)이 만족될 경우에 Bean으로 등록이 됨
	 */

	public static void main(String[] args) {
		SpringApplication.run(JinhaAssignmentApplication.class, args);
		System.out.print("Hello Spring Boot!");
	}

}
