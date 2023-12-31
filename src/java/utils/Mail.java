/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author LinhTM
 */
public class Mail {

    public void sentEmail(String toEmail, String subject, String content, String type) {
        String MAIL = "tranminhducbxvp@gmail.com";
        String PASSWORD = "voxwhknyrytimqxu";

        // Config
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);

        // Authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL, PASSWORD);
            }
        });

        // Mail info
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
      
            if (type.equals("link")) {
                message.setContent("<a href='" + content + "'>Click this to verify your mail</a>",
                        "text/html");
            } else if(type.equals("code")) {
                message.setContent("<p> Enter the following code to change your password: </P>"+"<p>"+ content.substring(0,6) +"</p>", "text/html");
            }

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String toEmail = "ductmhe173033@fpt.edu.vn";
        String subject = "test";
        String link = "http://localhost:9999/EasyTravel/profile";
        new Mail().sentEmail(toEmail, subject, link, "link");
    }

}
