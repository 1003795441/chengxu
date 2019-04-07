package com.vacn.chengxu.mavenProje;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vacn.chengxu.TestRegistryService;

public class TestDubbo {
   public static void main(String[] args) {
	   ApplicationContext ctx =new ClassPathXmlApplicationContext(new String []{"spring-dubbo-consumer.xml"});
	   TestRegistryService testRegistryService=(TestRegistryService)ctx.getBean("testRegistryService");
	   System.out.println(testRegistryService.hello("ADA"));
}
}
