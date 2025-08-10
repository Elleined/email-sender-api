package com.elleined.emailsenderapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void send_HappyPath() {
        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/simple")
                            .param("receiver", "receiver")
                            .param("subject", "subject")
                            .param("message", "message"))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");
    }

    @Test
    void sendAttachment_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
    }
}