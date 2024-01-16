package es.wacoco.nowle.Mail.Service.Imp;

public interface EmailService {
    void sendEmail(String from, String to, String subject, String text);
}
