package com.example.networkautomation;

import com.example.networkautomation.routes.PathsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class NetworkAutomationApplicationTests {

    @Autowired private PathsConfig pathsConfig;

    @Autowired private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertNotNull(pathsConfig);
        assertNotNull(mockMvc);
    }

    @Test
    @WithMockUser
    public void testAccessSecuredEndpointWithAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Expect OK status
    }

    @Test
    public void testSuccessfulLoginAndAccessSecuredEndpoint() throws Exception {
        MvcResult result =
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/login")
                                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                                        .param("username", "user")
                                        .param("password", "password"))
                        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                        .andReturn();

        String redirectedUrl = result.getResponse().getRedirectedUrl();
        Assertions.assertNotNull(redirectedUrl);

        // Add assertions to check if the redirection URL is as expected
        Assertions.assertTrue(redirectedUrl.endsWith("/hello"));
    }
}
