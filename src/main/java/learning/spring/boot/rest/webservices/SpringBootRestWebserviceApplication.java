package learning.spring.boot.rest.webservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringBootRestWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestWebserviceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
		return acceptHeaderLocaleResolver;
	}

	/**
	 * below method should allow as be messageSource. this bean can be replaced by
	 * using spring.messages.basename in application.properties
	 */
//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
//		bundleMessageSource.setBasename("message");
//		return bundleMessageSource;
//	}

}
