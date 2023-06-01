package pl.lilianakrol.quizzically.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lilianakrol.quizzically.exceptions.QuizzicallyException;
import pl.lilianakrol.quizzically.models.NotificationEmail;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {


    private final JavaMailSender mailSender;

    @RabbitListener(queues = "quizzically_verification_queue")
    void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("quizzically@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
        } catch (MailException e) {
            log.error("Exception occurred when sending email", e);
            throw new QuizzicallyException("Exception occurred when sending email to " + notificationEmail.getRecipient(), e);
        }
    }

}
