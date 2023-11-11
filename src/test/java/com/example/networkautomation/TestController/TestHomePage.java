package com.example.networkautomation.TestController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"welcomeMessage=test"})
public class TestHomePage {
    @Autowired
    public TestRestTemplate template;

    @Test
    public void getHello() throws Exception{
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("test");
    }

    @Test
    public void testHomePageStatus() {
        HttpStatus statusCode = (HttpStatus) template.exchange("/", HttpMethod.GET, null, String.class).getStatusCode();

        assertThat(statusCode).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testNotFoundPage() {
        ResponseEntity<String> response = template.getForEntity("/nonexistent", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("Not Found");
    }

}