package com.example.Ejercicio.back.services;

import com.example.Ejercicio.back.model.Mail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class MailServices {

    public void sendMail(Mail mail) {
        String to = mail.getDestinatario();
        String from = "librerialibr@gmail.com";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "mgvljmkdpbwqkaxe");
            }
        });


        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            if(to.contains(",")) {
                String recipients[] = to.split(", ");
                for (int i =0; i < recipients.length; i++) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            message.setSubject(mail.getAsunto());
            message.setText(mail.getMensaje());

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
