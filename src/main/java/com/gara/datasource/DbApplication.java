package com.gara.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author GARA
 */
@Component
@Slf4j
public class DbApplication {

    public static void main(String[] args) {

    }

    @EventListener(classes = ApplicationContextEvent.class)
    public void datasource() {

//        log.info("springApplication context started");
    }
}
