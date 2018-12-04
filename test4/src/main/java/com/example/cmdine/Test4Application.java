package com.example.cmdine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Test4Application implements CommandLineRunner {

	/*
	 * rough equivalent to creating a bean/using @Autowired
	 * //@Value("${spring.application.name}")
	 * //private String appName;
	 */
	@Autowired
	private AppName appName;
	
	@Bean
	public AppName getAppName(@Value("${spring.application.name}") String appName) {
		return new AppName() {
			@Override
			public String getName() {
				return appName;
			}
		};		
	}
	
	@Override
	public void run (String...args) throws Exception {
		System.out.println("run(): app=" + appName.getName() + ", #/args=" + args.length + "...");
		for (String arg : args) {
			System.out.println("  " + arg + "...");
		}
	}
		
	public static void main(String[] args) {
		System.out.println("main(): #/args=" + args.length + "...");
		SpringApplication.run(Test4Application.class, args);
	}
}
