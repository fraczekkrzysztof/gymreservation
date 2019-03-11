package com.fraczekkrzysztof.gymreservation.email;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;
import com.fraczekkrzysztof.gymreservation.entity.Reservation;

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
	public static final String EMAIL_FROM = "gymreservationdev@gmail.com";
	

	private void setCommonContext(Context theContext, Lesson theLesson, Reservation theReservation){
		theContext.setVariable("lesson", theLesson.getName());
		theContext.setVariable("date", theLesson.getDate());
		theContext.setVariable("trainer", theLesson.getTrainer().getName());
		theContext.setVariable("linkConfirm", generateConfirmationLink(theReservation.getId()));
		theContext.setVariable("linkCancel", generateCancelLink(theReservation.getId()));
	}
	
	public Context generateReservationEmail(Lesson theLesson, Reservation theReservation) {
		Context theContext = new Context();
		theContext.setVariable("message", "Reservation succed!");
		setCommonContext(theContext, theLesson, theReservation);
		return theContext;
		
	}

	public Context generateReservationEmailForFirstWaiting(Lesson theLesson, Reservation theReservation){
		Context theContext = new Context();
		theContext.setVariable("message", "Empty place - please confirmed within 24 hours");
		setCommonContext(theContext, theLesson, theReservation);
		return theContext;
	}
	
	private String generateCancelLink(int theId) {
		String url = "http://localhost:8080/api/reservation/" + theId + "/cancel";
		return url;
	}

	private String generateConfirmationLink(int theId) {
		String url = "http://localhost:8080/api/reservation/" + theId + "/confirm";
		return url;
	}
	
	public void sendEmail(String to,String subject, String templateName, Context context) {
		String body = templateEngine.process(templateName, context);
		try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setFrom(EMAIL_FROM);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
           
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
        }
		 	
	}
	
}
