package com.elleined.emailsenderapi;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
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

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

    private ExecutableValidator executableValidator;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private MailServiceImpl mailService;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        this.executableValidator = validator.forExecutables();

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

    static Stream<Arguments> simpleMailInvalidInputs() {
        return Stream.of(
                // Null inputs
                Arguments.of(null, "subject", "message"),
                Arguments.of("receiver@gmail.com", null, "message"),
                Arguments.of("receiver@gmail.com", "subject", null),

                // Blank inputs
                Arguments.of( "   ", "subject", "message"),
                Arguments.of("receiver@gmail.com", "   ", "message"),
                Arguments.of("receiver@gmail.com", "subject", "  ")
        );
    }

    @ParameterizedTest
    @MethodSource("simpleMailInvalidInputs")
    void simpleMail_ShouldThrowBindException_ForInvalidInputs(String email, String subject, String message) throws NoSuchMethodException {
        // Pre defined values

        // Expected Value

        // Mock data
        Set<ConstraintViolation<MailService>> violations = executableValidator.validateParameters(
                mailService,
                MailServiceImpl.class.getMethod("send", String.class, String.class, String.class),
                new Object[]{email, subject, message}
        );
        System.out.println(violations.size());

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

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