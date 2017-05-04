package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService{
	@Autowired
    private MailSender mailSender;

	public MailService() {
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(Student student, String accessCode) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom("mumpmproject@gmail.com");
		message.setTo(student.getEmail());
		message.setSubject("Test");
		message.setText(emailMessageBuilder(student, accessCode));
		try {
			mailSender.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private String emailMessageBuilder(Student student, String accessCode){
		return "Hello " + student.getFirstName()+" "+student.getLastName() + ", the link for your test is: "+ "http://localhost:8080/student/test \n" +
				" And the Test Key is: " + accessCode;

	}
}