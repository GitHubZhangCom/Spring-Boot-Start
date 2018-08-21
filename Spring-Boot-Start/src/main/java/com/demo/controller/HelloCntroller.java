package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCntroller {

	@RequestMapping("/hello")
	public String hello() {
		return "Hello Spring boot ~~~";
	}
	@RequestMapping("/sayHello")
	public String sayHello() {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("测试 say hello !!!!", HttpStatus.OK);
	    return responseEntity.getBody();
	}
	
	@RequestMapping("/sayhello2")
	public String sayHello2() {
	    Map<String, String> map = new HashMap<>();
	    map.put("name", "李四");
	    ResponseEntity<String> responseEntity =new ResponseEntity<String>("测试 say hello !!!!{", HttpStatus.OK); 
	    return responseEntity.getBody();
	}
	
	public static void main(String[] args) {
		String term="3|6|9|12";
		String strs[]=term.split("\\|");
		System.out.println(strs.toString());
	}
	
}
