package com.elleined.emailsenderapi;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test", "test"))
            .withPerMethodLifecycle(false);

    @Test
    void send_HappyPath() {
        String receiver = "test@gmail.com";
        String subject = "subject";
        String message = "message";

        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/simple")
                            .param("receiver", receiver)
                            .param("subject", subject)
                            .param("message", message))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");

        boolean mailReceived = greenMail.waitForIncomingEmail(20_000, 1);
        assertThat(mailReceived).isTrue();
    }

    @Test
    void sendAttachment_HappyPath() {
        String receiver = "test@gmail.com";
        String subject = "subject";
        String message = "message";
        byte[] bytes = new byte[0];

        assertDoesNotThrow(() -> {
            mockMvc.perform(multipart("/attachment")
                            .file("attachment", bytes)
                            .param("receiver", receiver)
                            .param("subject", subject)
                            .param("message", message))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");

        boolean mailReceived = greenMail.waitForIncomingEmail(10_000, 1);
        assertThat(mailReceived).isTrue();
    }
}