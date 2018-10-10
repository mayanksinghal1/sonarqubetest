package net.atos.Customer.config;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author A666395 This class for run the SpringBootWebApplicatione
 *
 */

@SpringBootApplication
@ComponentScan(basePackages = { "net.atos.Customer" })
public class SpringBootWebApplication extends SpringBootServletInitializer {
	private static final Logger logger = Logger.getLogger(SpringBootWebApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}
