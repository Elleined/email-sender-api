package com.elleined.emailsenderapi;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

    private static ExecutableValidator executableValidator;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    @BeforeAll
    static void setupAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        executableValidator = validator.forExecutables();
    }

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

    private static Stream<Arguments> nullAndBlankValues() {
        String receiver = "receiver@gmail.com";
        String subject = "subject";
        String message = "message";

        return Stream.of(
                Arguments.of(null, subject, message),
                Arguments.of(receiver, null, message),
                Arguments.of(receiver, subject, null),

                Arguments.of("   ", subject, message),
                Arguments.of(receiver, "   ", message),

                Arguments.of("", subject, message),
                Arguments.of(receiver, "", message)
        );
    }

    @ParameterizedTest
    @MethodSource("nullAndBlankValues")
    void simpleMail_ShouldThrowConstraintViolationException_ForNullAndBlankInputs(String receiver, String subject, String message) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<MailService>> violations = executableValidator.validateParameters(
                mailService,
                MailService.class.getMethod("send", String.class, String.class, String.class),
                new Object[]{receiver, subject, message}
        );

        // Behavior Verifications
        verifyNoInteractions(mailSender);

        // Assertions
        assertFalse(violations.isEmpty());
    }

    @Test
    void attachmentMail_HappyPath() throws IOException {
        // Pre defined values

        // Expected Value

        // Mock data
        MultipartFile file = mock(MultipartFile.class);

        // Set up method
        when(file.getBytes()).thenReturn(new byte[0]);
        when(file.getOriginalFilename()).thenReturn("originalFileName");
        MimeMessage mimeMessage = mock(MimeMessage.class);

        // Stubbing methods
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        doNothing().when(mailSender).send(any(MimeMessage.class));

        // Calling the method
        assertDoesNotThrow(() -> mailService.send("test@gmail.com", "subject", "message", file.getOriginalFilename(), file.getBytes()));

        // Behavior Verifications
        verify(mailSender).createMimeMessage();
        verify(mailSender).send(any(MimeMessage.class));

        // Assertions
    }

    private static Stream<Arguments> attachmentMail_NullAndBlankInputs() throws IOException {
        String receiver = "receiver@gmail.com";
        String subject = "subject";
        String message = "message";
        MockMultipartFile attachment = new MockMultipartFile("fileName", new byte[0]);
        String fileName = attachment.getOriginalFilename();
        byte[] bytes = attachment.getBytes();

        return Stream.of(
                Arguments.of(null, subject, message, fileName, bytes),
                Arguments.of(receiver, null, message, fileName, bytes),
                Arguments.of(receiver, subject, null, fileName, bytes),

                Arguments.of("   ", subject, message, fileName, bytes),
                Arguments.of(receiver, "   ", message, fileName, bytes),
                Arguments.of(receiver, subject, "   ", fileName, bytes),
                Arguments.of(receiver, subject, message, "    ", bytes),

                Arguments.of("", subject, message, fileName, bytes),
                Arguments.of(receiver, "", message, fileName, bytes),
                Arguments.of(receiver, subject, "", fileName, bytes),
                Arguments.of(receiver, subject, message, "", bytes)
        );
    }

    @ParameterizedTest
    @MethodSource("attachmentMail_NullAndBlankInputs")
    void attachmentMail_ShouldThrowConstraintViolationException_ForNullInputs(String email, String subject, String message, String fileName, byte[] bytes) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        Set<ConstraintViolation<MailService>> violations = executableValidator.validateParameters(
                mailService,
                MailService.class.getMethod("send", String.class, String.class, String.class, String.class, byte[].class),
                new Object[]{email, subject, message, fileName, bytes}
        );

        // Behavior Verifications
        verifyNoInteractions(mailSender);

        // Assertions
        assertFalse(violations.isEmpty());
    }
}