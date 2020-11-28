package learning.spring.boot.rest.webservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import learning.spring.boot.rest.webservices.helloworld.model.HelloWorldBean;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// @RequestMapping(method = RequestMethod.GET,path = "/hello-world")|
	@GetMapping(path = "/hello-world")
	public String getWelcomeMessage() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean getWelcomeMessageBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean getWelcomeMessageBeanWithPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	@GetMapping("/hello-world-i18n")
	public String getHelloWorldI18n(@RequestHeader(value = "Accept-language", required = false) Locale locale) {
		return messageSource.getMessage("hello.world.message", null, locale);
	}

	@GetMapping("/hello-world-i18n-v2")
	public String getHelloWorldI18nV2() {
		return messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
	}

}
