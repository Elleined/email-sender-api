package com.elleined.emailsenderapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

        // Expected Value

        // Mock data
        String receiver = "receiver";
        String subject = "subject";
        int otp = 1;
        int plusExpirationSeconds = 60;

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
                    .andExpect(jsonPath("$.receiver", is(receiver)))
                    .andExpect(jsonPath("$.subject", is(subject)))
                    .andExpect(jsonPath("$.otp", notNullValue()))
                    .andExpect(jsonPath("$.expiration", notNullValue()));
        }, "endpoint changed or doesn't exists anymore");

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }

    @Test
    void OTPMail_ShouldUse60Seconds_DefaultPlusExpirationSeconds_WhenNotProvided() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data
        String receiver = "test@gmail.com";
        String subject = "subject";

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString());
        when(secureRandom.nextInt(anyInt(), anyInt())).thenReturn(1);

        // Calling the method
        MvcResult mvcResult = assertDoesNotThrow(() -> mockMvc.perform(post("/otp")
                        .param("receiver", receiver)
                        .param("subject", subject))
                .andExpect(status().isAccepted())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.receiver", is(receiver)))
                .andExpect(jsonPath("$.subject", is(subject)))
                .andExpect(jsonPath("$.otp", notNullValue()))
                .andExpect(jsonPath("$.expiration", notNullValue()))
                .andReturn());

        OTPMessage otpMessage = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OTPMessage.class);
        long difference = Duration.between(Instant.now(), otpMessage.expiration()).toSeconds();

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
        assertThat(difference).isBetween(59L, 61L);
    }

    @Test
    void OTPMail_ShouldReturnInternalServerError_ForMessagingException() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data
        String receiver = "test@gmail.com";
        String subject = "subject";

        // Set up method

        // Stubbing methods
        doThrow(MessagingException.class).when(mailService).send(anyString(), anyString(), anyString());
        when(secureRandom.nextInt(anyInt(), anyInt())).thenReturn(1);

        // Calling the method
        mockMvc.perform(post("/otp")
                        .param("receiver", receiver)
                        .param("subject", subject)
                        .param("plusExpirationSeconds", String.valueOf(120)))
                .andExpect(status().isInternalServerError());

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }
}