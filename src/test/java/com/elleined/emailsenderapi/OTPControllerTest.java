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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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

    static Stream<Arguments> otpMailNullInputs() {
        return Stream.of(
                Arguments.of(null, "subject", 120),
                Arguments.of("receiver@gmail.com", null, 120),
                Arguments.of("receiver@gmail.com", "subject", null)
        );
    }

    @ParameterizedTest
    @MethodSource("otpMailNullInputs")
    void OTPMail_ShouldReturnBadRequest_ForNullInputs(String receiver, String subject, Integer plusExpirationSeconds) throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        mockMvc.perform(post("/otp")
                        .param("receiver", receiver)
                        .param("subject", subject)
                        .param("plusExpirationSeconds", String.valueOf(plusExpirationSeconds)))
                .andExpect(status().isBadRequest());

        // Behavior Verifications
        verifyNoInteractions(mailService, secureRandom);

        // Assertions
    }

    @Test
    void OTPMail_ShouldUse60SecondsDefaultPlusExpirationSeconds_WhenNotProvided() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString());
        when(secureRandom.nextInt(anyInt(), anyInt())).thenReturn(1);

        // Calling the method
        MvcResult mvcResult = mockMvc.perform(post("/otp")
                        .param("receiver", "receiver")
                        .param("subject", "subject"))
                .andExpect(status().isAccepted())
                .andReturn();

        OTPMessage otpMessage = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OTPMessage.class);
        long difference = Duration.between(LocalDateTime.now(), otpMessage.expiration()).getSeconds();

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
        assertTrue(difference >= 59 && difference <= 61);
    }

    @Test
    void OTPMail_ShouldThrowMessageException() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        doThrow(MessagingException.class).when(mailService).send(anyString(), anyString(), anyString());
        when(secureRandom.nextInt(anyInt(), anyInt())).thenReturn(1);

        // Calling the method
        mockMvc.perform(post("/otp")
                        .param("receiver", "receiver")
                        .param("subject", "subject")
                        .param("plusExpirationSeconds", String.valueOf(120)))
                .andExpect(status().isInternalServerError());

        // Behavior Verifications
        verify(secureRandom).nextInt(anyInt(), anyInt());
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }
}