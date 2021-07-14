package com.example.demo.service;

import com.example.demo.config.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CronJobService {

    // Replace with your email here:
    public static final String MY_EMAIL = "sonbuitung663@gmail.com";

    // Replace password!!
    public static final String MY_PASSWORD = "10220119";

    // And receiver!
    public static final String FRIEND_EMAIL = "son.buitung.ncc@gmail.com";

    @Autowired
    private JavaMailSender emailSender;


    @Scheduled(fixedDelay = 1 * 1000 * 60)
//    @Scheduled(cron = "0 0 12 * * ?")
    public void CronJobService() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        message.setContent(htmlMsg, "text/html");

        helper.setTo(MyConstants.FRIEND_EMAIL);

        helper.setSubject("Test send HTML email");

        this.emailSender.send(message);
    }


}
