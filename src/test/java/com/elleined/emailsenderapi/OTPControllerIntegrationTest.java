package com.elleined.emailsenderapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OTPControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("test", "test"))
            .withPerMethodLifecycle(false);

    @Test
    void send_HappyPath() throws UnsupportedEncodingException, JsonProcessingException {
        String receiver = "test@gmail.com";
        String subject = "subject";
        int plusExpirationSeconds = 120;

        MvcResult mvcResult = assertDoesNotThrow(() -> mockMvc.perform(post("/otp")
                        .param("receiver", receiver)
                        .param("subject", subject)
                        .param("plusExpirationSeconds", String.valueOf(plusExpirationSeconds)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.receiver", is(receiver)))
                .andExpect(jsonPath("$.subject", is(subject)))
                .andExpect(jsonPath("$.otp", notNullValue()))
                .andExpect(jsonPath("$.expiration", notNullValue()))
                .andReturn());

        OTPMessage otpMessage = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OTPMessage.class);
        long difference = Duration.between(Instant.now(), otpMessage.expiration()).toSeconds();
        boolean mailReceived = greenMail.waitForIncomingEmail(500, 1);

        assertThat(mailReceived).isTrue();
        assertThat(difference).isBetween(119L, 121L);
    }

    @Test
    void OTPMail_ShouldReturnBadRequest_ForLessThanZeroPlusExpirationSeconds() throws UnsupportedEncodingException, JsonProcessingException {
        String receiver = "test@gmail.com";
        String subject = "subject";

        MvcResult mvcResult = assertDoesNotThrow(() -> mockMvc.perform(post("/otp")
                        .param("receiver", receiver)
                        .param("subject", subject))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.receiver", is(receiver)))
                .andExpect(jsonPath("$.subject", is(subject)))
                .andExpect(jsonPath("$.otp", notNullValue()))
                .andExpect(jsonPath("$.expiration", notNullValue()))
                .andDo(print())
                .andReturn());

        OTPMessage otpMessage = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OTPMessage.class);
        long difference = Duration.between(Instant.now(), otpMessage.expiration()).toSeconds();
        boolean mailReceived = greenMail.waitForIncomingEmail(500, 1);

        assertThat(mailReceived).isTrue();
        assertThat(difference).isBetween(59L, 61L);
    }
}