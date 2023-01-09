package com.babatunde.learningspring.util;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String hostUrl = event.getApplicationContext().getEnvironment().getProperty("local.server.port");
        System.out.println("Application started on port: " + hostUrl);
    }
}
