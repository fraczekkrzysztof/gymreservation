package com.fraczekkrzysztof.gymreservation.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;

@Component
public class EmailSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
	@Autowired
    private TemplateEngine templateEngine;
	@Autowired
    private JavaMailSender javaMailSender;

	public static final String EMAIL_TOPIC_RESERVATION = "New reservation";
	public static final String EMAIL_TOPIC_WAITING = "New reservation - waiting list";
	public static final String EMAIL_TOPIC_AVAILABLE = "New reservation - available";
	public static final String TEMPLATE_NAME = "template.html";
	
	
	
	public Context generateReservationEmail(Lesson theLesson) {
		Context theContext = new Context();
		theContext.setVariable("message", "Reservation succed!");
		theContext.setVariable("lesson", theLesson.getName());
		theContext.setVariable("date", theLesson.getDate());
		theContext.setVariable("trainer", theLesson.getTrainer().getName());
		return theContext;
		
	}
	
	public void sendEmail(String to,String subject, String templateName, Context context) {
		String body = templateEngine.process(templateName, context);
		try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
        }
		 	
	}
	
}
