package com.learn.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
    	
        try {
			return "Greetings from Spring Boot! from host: " + InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        return "Greetings from Spring Boot! from host: Counld not get hostname";
    }

}