package com.boot.accellearn.services;

import java.util.Locale;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.accellearn.model.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
	@GetMapping(path = "/helloWorld")
	public String wishHello() {
		return "Hello World";
	}
	
	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean wishHelloBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/helloWorldBean/{name}")
	public HelloWorldBean wishHelloBean(@PathVariable String name) {
		HelloWorldBean bean = new HelloWorldBean("Hello World");
		bean.setName(name);
		return bean;
	}

	@GetMapping(path = "/i18nGoodMorning")
	public String wishGoodMorning() {//@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
	}
}
