package com.elleined.emailsenderapi;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(mailService, "sender", "test@gmail.com");
    }

    @Test
    void simpleMail_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        MimeMessage mimeMessage = mock(MimeMessage.class);

        // Stubbing methods
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mailSender).send(any(MimeMessage.class));

        // Calling the method
        assertDoesNotThrow(() -> mailService.send("test@gmail.com", "subject", "message"));

        // Behavior Verifications
        verify(mailSender).createMimeMessage();
        verify(mailSender).send(any(MimeMessage.class));

        // Assertions
    }

    @Test
    void attachmentMail_HappyPath() {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method
        MimeMessage mimeMessage = mock(MimeMessage.class);

        // Stubbing methods
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        doNothing().when(mailSender).send(any(MimeMessage.class));

        // Calling the method
        assertDoesNotThrow(() -> mailService.send("test@gmail.com", "subject", "message", new MockMultipartFile("attachment", new byte[]{})));

        // Behavior Verifications
        verify(mailSender).createMimeMessage();
        verify(mailSender).send(any(MimeMessage.class));

        // Assertions
    }
}