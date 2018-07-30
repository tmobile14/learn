package com.learn.example.demo1;

import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
    	
        try {
        	InetAddress inetAddress = InetAddress.getByName("demo-svc");
			return "New Greetings from Spring Boot! from host: " + inetAddress.getHostAddress() + " " + inetAddress.getHostName() + " " + inetAddress.getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        return "New Greetings from Spring Boot! from host: Counld not get hostname";
    }

}