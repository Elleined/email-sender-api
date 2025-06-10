package com.elleined.emailsenderapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@WebMvcTest(OTPController.class)
class OTPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MailService mailService;

    @MockitoBean
    private SecureRandom secureRandom;

    @Test
    void OTPMail_HappyPath() throws MessagingException {
        // Pre defined values
        String receiver = "receiver";
        String subject = "subject";
        int otp = 1;
        int plusExpirationSeconds = 60;

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString());
        when(secureRandom.nextInt(anyInt(), anyInt())).thenReturn(otp);

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(post("/otp")
                    .param("receiver", receiver)
                    .param("subject", subject)
                    .param("plusExpirationSeconds", String.valueOf(plusExpirationSeconds)))
                    .andExpect(status().isAccepted())
                    .andExpect(jsonPath("$.receiver").value(receiver))
                    .andExpect(jsonPath("$.subject").value(subject))
                    .andExpect(jsonPath("$.otp").value(otp))
                    .andExpect(jsonPath("$.expiration").exists());
        }, "endpoint changed or doesn't exists anymore");

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }
}