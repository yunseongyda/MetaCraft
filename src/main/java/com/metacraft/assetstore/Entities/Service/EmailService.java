package com.metacraft.assetstore.Entities.Service;

import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender mailSender;

  public void sendEmail(String userEmail, String subject, String content) {
    try {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
        helper.setTo("khw3335@gmail.com"); // 내 이메일로 수신
        helper.setSubject("문의: " + subject);
        helper.setText("보낸 사람: " + userEmail + "\n\n" + content);
        helper.setReplyTo(userEmail); // 답장할 때 사용자 이메일로 가게 설정

        mailSender.send(message);
    } catch (MessagingException e) {
        e.printStackTrace();
        // 이메일 전송 실패 처리 로직 추가
    }
  }
}
