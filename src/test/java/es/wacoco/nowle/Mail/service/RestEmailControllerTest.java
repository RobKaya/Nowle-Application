package es.wacoco.nowle.Mail.service;

import es.wacoco.nowle.Mail.RestEmailController;
import es.wacoco.nowle.Mail.Service.Imp.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestEmailController.class)
public class RestEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private RestEmailController restEmailController;

    @Test
    public void testSendEmail_Success() throws Exception {
        // Mock the behavior of the emailService
        doNothing().when(emailService).sendEmail(any(), any(), any(), any());

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/email/send")
                        .param("from", "test@example.com")
                        .param("to", "recipient@example.com")
                        .param("subject", "Test Subject")
                        .param("text", "Test Body")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent successfully!"));
    }

    @Test
    public void testSendEmail_Failure() throws Exception {
        // Mock the behavior of the emailService to simulate an exception
        doThrow(new RuntimeException("Simulated error")).when(emailService).sendEmail(any(), any(), any(), any());

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/email/send")
                        .param("from", "test@example.com")
                        .param("to", "recipient@example.com")
                        .param("subject", "Test Subject")
                        .param("text", "Test Body")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error sending email. Please try again."));
    }
}

