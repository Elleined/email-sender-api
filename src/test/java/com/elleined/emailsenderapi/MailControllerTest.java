package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MailController.class)
class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MailService mailService;

    @Test
    void simpleMail_HappyPath() throws MessagingException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/simple")
                    .param("receiver", "receiver")
                    .param("subject", "subject")
                    .param("message", "message"))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");

        // Behavior Verifications
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }

    @Test
    void attachmentMail_HappyPath() throws MessagingException, IOException {
        // Pre defined values

        // Expected Value

        // Mock data
        MockMultipartFile attachment = new MockMultipartFile("attachment", "attachment.txt", MediaType.TEXT_PLAIN_VALUE, "attachment".getBytes());

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString(), any(MultipartFile.class));

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(multipart("/attachment")
                            .file(attachment)
                            .param("receiver", "receiver")
                            .param("subject", "subject")
                            .param("message", "message"))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");

        // Behavior Verifications
        verify(mailService).send(anyString(), anyString(), anyString(), any(MultipartFile.class));

        // Assertions
    }
}