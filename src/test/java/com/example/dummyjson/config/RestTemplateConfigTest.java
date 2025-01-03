package com.example.dummyjson.config;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class RestTemplateConfigTest {

	@Autowired
	WebClientConfig webClientConfig;

    @Test
    public void testRestTemplateConfig(){
        Assert.assertNotNull(this.webClientConfig.webClient());
    }
}
