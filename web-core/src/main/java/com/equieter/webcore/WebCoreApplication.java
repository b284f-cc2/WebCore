package com.equieter.webcore;

import com.equieter.weblog.helper.LogHelper;
import com.equieter.weblog.logger.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebCoreApplication extends SpringBootServletInitializer {

	private static Logger LOG = LogHelper.genLogger();

	public static void main(String[] args) {
		LOG.debug("Server Start!");
		SpringApplication.run(WebCoreApplication.class, args);
	}

}
