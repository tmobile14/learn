package com.ericsson.bss.ms.webservices.post.hello;

public class HelloWorldBean {
	String greeting;
	HelloWorldBean(String greeting) {
		this.greeting = greeting;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("HellowWorldBean [message=%s]", greeting);
	}
	
	
	
}
