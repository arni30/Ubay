package world.ucode.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import world.ucode.models.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class SendMail {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SimpleMailMessage templateMessage;
    @Async
    public void sendMail(User user) throws UnknownHostException {
//        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//        context.load("classpath:applicationContext.xml");
//        context.refresh();
//         = context.getBean("mailSender", JavaMailSender.class);
//        SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);

        // Создаём потокобезопасную копию шаблона.
        if (mailSender == null) {
            System.out.println("HALLLLLLLO");
        }
        SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);

        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registration confirmation");
        mailMessage.setText("To confirm your account, please click here : "
                + "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/ubay/confirmation/?token=" + user.getToken());
         mailSender.send(mailMessage);
        System.out.println("Mail sended");

    }
}