package org.yundaxue.workshop.acq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AcqApplication extends SpringBootServletInitializer {

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application){
        return application.sources(AcqApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AcqApplication.class, args);
    }
}
