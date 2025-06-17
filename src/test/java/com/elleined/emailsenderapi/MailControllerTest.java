package com.elleined.emailsenderapi;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

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

    private static Stream<Arguments> simpleMailNullInputs() {
        return Stream.of(
                Arguments.of(null, "subject", "message"),
                Arguments.of("receiver@gmail.com", null, "message"),
                Arguments.of("receiver@gmail.com", "subject", null)
        );
    }

    @ParameterizedTest
    @MethodSource("simpleMailNullInputs")
    void simpleMail_ShouldReturnBadRequest_ForNullInputs(String receiver, String subject, String message) throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        mockMvc.perform(post("/simple")
                        .param("receiver", receiver)
                        .param("subject", subject)
                        .param("message", message))
                .andExpect(status().isBadRequest());

        // Behavior Verifications
        verifyNoInteractions(mailService);

        // Assertions
    }

    @Test
    void simpleMail_ShouldReturnInternalServerError_ForMessagingException() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        doThrow(MessagingException.class).when(mailService).send(anyString(), anyString(), anyString());

        // Stubbing methods

        // Calling the method
        mockMvc.perform(post("/simple")
                        .param("receiver", "receiver")
                        .param("subject", "subject")
                        .param("message", "message"))
                .andExpect(status().isInternalServerError());

        // Behavior Verifications
        verify(mailService).send(anyString(), anyString(), anyString());

        // Assertions
    }

    @Test
    void attachmentMail_HappyPath() throws MessagingException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        doNothing().when(mailService).send(anyString(), anyString(), anyString(), anyString(), any());

        // Calling the method
        assertDoesNotThrow(() -> {
            mockMvc.perform(multipart("/attachment")
                            .file(MockFile.get())
                            .param("receiver", "receiver")
                            .param("subject", "subject")
                            .param("message", "message"))
                    .andExpect(status().isAccepted());
        }, "endpoint changed or doesn't exists anymore");

        // Behavior Verifications
        verify(mailService).send(anyString(), anyString(), anyString(), anyString(), any());

        // Assertions
    }

    @ParameterizedTest
    @MethodSource("simpleMailNullInputs")
    void attachmentMail_ShouldReturnBadRequest_ForNullInputs(String receiver, String subject, String message) throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        mockMvc.perform(multipart("/attachment")
                        .file(MockFile.get())
                        .param("receiver", receiver)
                        .param("subject", subject)
                        .param("message", message))
                .andExpect(status().isBadRequest());

        // Behavior Verifications
        verifyNoInteractions(mailService);

        // Assertions
    }

    @Test
    void attachmentMail_ShouldReturnBadRequest_ForMissingFile() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        mockMvc.perform(multipart("/attachment")
                        .param("receiver", "receiver")
                        .param("subject", "subject")
                        .param("message", "message"))
                .andExpect(status().isBadRequest());

        // Behavior Verifications
        verifyNoInteractions(mailService);

        // Assertions
    }

    @Test
    void attachmentMail_ShouldReturnInternalServerError_ForMessagingException() throws Exception {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        doThrow(MessagingException.class).when(mailService).send(anyString(), anyString(), anyString(), anyString(), any());

        // Stubbing methods

        // Calling the method
        mockMvc.perform(multipart("/attachment")
                        .file(MockFile.get())
                        .param("receiver", "receiver")
                        .param("subject", "subject")
                        .param("message", "message"))
                .andExpect(status().isInternalServerError());

        // Behavior Verifications
        verify(mailService).send(anyString(), anyString(), anyString(), anyString(), any());

        // Assertions
    }
}