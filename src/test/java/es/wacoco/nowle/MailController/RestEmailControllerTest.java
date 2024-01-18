package es.wacoco.nowle.MailController;

import es.wacoco.nowle.Mail.Controller.RestEmailController;
import es.wacoco.nowle.Mail.Service.Imp.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

public class RestEmailControllerTest {

    @Test
    public void testSendEmailSuccess() {
        EmailService mockEmailService = Mockito.mock(EmailService.class);
        RestEmailController controller = new RestEmailController(mockEmailService);

        ResponseEntity<String> response = controller.sendEmail("from@example.com", "to@example.com", "Subject", "Text");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "The status code should be OK");
        assertEquals("Email sent successfully!", response.getBody(), "The response body should indicate success");
    }

    @Test
    public void testSendEmailFailure() {
        EmailService mockEmailService = Mockito.mock(EmailService.class);
        doThrow(new RuntimeException("Failed to send email")).when(mockEmailService).sendEmail("from@example.com", "to@example.com", "Subject", "Text");

        RestEmailController controller = new RestEmailController(mockEmailService);

        ResponseEntity<String> response = controller.sendEmail("from@example.com", "to@example.com", "Subject", "Text");
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "The status code should be INTERNAL_SERVER_ERROR");
        assertEquals("Error sending email. Please try again.", response.getBody(), "The response body should indicate an error");
    }
}
