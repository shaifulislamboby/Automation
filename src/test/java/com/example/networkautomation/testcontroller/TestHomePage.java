package com.example.networkautomation.testcontroller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

/**
 * This is a Javadoc comment. It provides information about the method or class. You can include a
 * description, parameter explanations, return value, exceptions, etc.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"welcomeMessage=test"})
public class TestHomePage {
    @Autowired private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("test");
    }
    /**
     * This is a Javadoc comment. It provides information about the method or class. You can include
     * a description, parameter explanations, return value, exceptions, etc.
     */
    @Test
    public void testHomePageStatus() {
        HttpStatus statusCode =
                (HttpStatus)
                        template.exchange("/", HttpMethod.GET, null, String.class).getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
    }
}
