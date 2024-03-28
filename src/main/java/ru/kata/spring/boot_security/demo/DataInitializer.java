package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.utils.Utils;

@Component
public class DataInitializer implements CommandLineRunner {

    private final Utils utils;

    @Autowired
    public DataInitializer(Utils utils) {
        this.utils = utils;
    }

    @Override
    public void run(String... args) throws Exception {
        utils.createMockData();
    }
}
