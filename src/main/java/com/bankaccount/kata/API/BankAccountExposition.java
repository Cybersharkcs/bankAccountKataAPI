package com.bankaccount.kata.API;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bankaccount.kata.API" })
public class BankAccountExposition {
    public static void main(final String... args) {
        final SpringApplicationBuilder application = new SpringApplicationBuilder();
        application.sources(BankAccountExposition.class).run(args);
    }
}
