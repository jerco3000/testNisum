package com.prueba.nisum.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WebConfigTest {

    @Autowired
    private WebConfig webConfig;

    @Test
    public void addInterceptorsTest() {
        InterceptorRegistry registry = new InterceptorRegistry();
        webConfig.addInterceptors(registry);
        assertNotNull(registry);
    }
}

