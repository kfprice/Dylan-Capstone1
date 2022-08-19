package com.hcl.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



interface EmailSender {
    void send(String to, String email);
    void sendOrder(String to, String email);
}

@Service
@AllArgsConstructor
@Async
public class EmailService implements EmailSender{

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body);
            helper.setTo(to);
            helper.setSubject("Confirmation Email From Ecommerce App");
            helper.setFrom("ecommerceapp@test.com");
            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new IllegalStateException("Email Failed");
        }
    }
    public void sendOrder(String to, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body);
            helper.setTo(to);
            helper.setSubject("Your Order Details");
            helper.setFrom("ecommerceapp@test.com");
            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new IllegalStateException("Email Failed");
        }
    }
}
