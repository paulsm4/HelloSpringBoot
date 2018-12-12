package com.example.test7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test7Controller {
	
	private static Logger logger = LoggerFactory.getLogger("test7");
	
	public Test7Controller () {
    	logger.info("Test7Controller constructor...");
	}
	
//    @GetMapping("/")
//    public String getIndex() {
//    	logger.info("Test7Controller.getIndex()...");
//        return "test";
//    }

    @GetMapping("/jsp")
    public String getJsp() {
    	logger.info("Test7Controller.getJsp()...");
        return "test";
    }
    
    @GetMapping("/thymeleaf")
    public String getThymeleaf() {
    	logger.info("Test7Controller.getThymeleaf()...");
        return "test.html";
    }
}
