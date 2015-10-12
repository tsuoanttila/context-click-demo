package org.vaadin.teemusa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "org.vaadin.fwteam", "org.vaadin.teemusa" })
public class KesakkoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KesakkoDemoApplication.class, args);
    }
}
