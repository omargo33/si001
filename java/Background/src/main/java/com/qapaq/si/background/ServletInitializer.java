package com.qapaq.si.background;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan({
	"com.qapaq.si.background.servicio",		
	"com.qapaq.si.background.scheduler",
}
)	
@EnableJpaRepositories("com.qapaq.si.background.jpa.queries")
@EnableScheduling
@EntityScan("com.qapaq.si.background.jpa.model")
@SpringBootApplication
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackgroundApplication.class);
	}
}
