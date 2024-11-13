package com.reichsacht.v_chan.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;


@Service
public class EmailVerificationService {
	@Autowired
	private JavaMailSender emailsender;
	public void sendVerificationEmail(String toemail, String verificationCode) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toemail);
		message.setSubject("Codigo de verificacion");
		message.setText("Tu codigo de verificacion:: "+verificationCode);
		emailsender.send(message);
	}
	public String generatedVerificationCode() {
		Random r = new Random();
		int code = 100000+r.nextInt(999999);
		return String.valueOf(code);
	}
}
