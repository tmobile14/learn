package com.ericsson.bss.ms.webservices.post.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource;
	
	@GetMapping("/hello")
	String hello() {
		return "Hello World!!";
	}

	@GetMapping("/helloworldbean")
	HelloWorldBean helloBean() {
		return new HelloWorldBean("Hello World!!");
	}

	@GetMapping("/helloworldbeani18n")
	HelloWorldBean helloBeani18n() {
		return new HelloWorldBean(messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()));
	}

	@GetMapping("/helloworldbean/path-variable/{name}")
	HelloWorldBean helloBeanPath(@PathVariable String name) {
		return new HelloWorldBean("Hello World!!: " + name);
	}

	
}
