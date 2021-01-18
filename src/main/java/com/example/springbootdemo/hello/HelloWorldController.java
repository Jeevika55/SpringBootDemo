package com.example.springbootdemo.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource bundleMessageSource;

	//@RequestMapping(method= RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Jeevika","P","Bangalore");
	}
	
	@GetMapping("/helloworld-int")
	public String getMessagesInI18Nformat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return bundleMessageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/helloworld-int2")
	public String getMessagesInI18Nformat2() {
		return bundleMessageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
	
}
