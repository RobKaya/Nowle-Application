package es.wacoco.nowle.Mail;

import es.wacoco.nowle.Mail.Service.Imp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/email")
public class RestEmailController {

    private final EmailService emailService;

    @Autowired
    public RestEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text
    ) {
        try {
            emailService.sendEmail(from, to, subject, text);
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email. Please try again.");
        }
    }
}
