package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.example.demo.Model.Student;

public class EmailNotificationClass {
	
	 JavaMailSender jms;
		 public EmailNotificationClass(JavaMailSender jms) {
			 this.jms=jms;
		 }
		
		@Async
	public void CallingEmailNotificationMethod(Student p1) {

		try {
//			Properties prop=new Properties();
//			prop.put("mail.smtp.host","smtp.gmail.com");
//			prop.put("mail.smtp.starttls.enable", "true");
			System.out.println(p1.getEmail());
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(p1.getEmail());
		mail.setFrom("santhoshbollena@gmail.com");
		mail.setSubject("Registration Notification");
		mail.setText("You have been registered to our student management Portal     Name ="+p1.getName()+"  Age ="+p1.getAge()+"  Country ="+p1.getCountry()+" , thank you");
		System.out.println(mail.getText());
		jms.send(mail);
		
		}catch(Exception ex) {
			System.out.println("exception "+ex);
		}
		System.out.println("email sent");
	}
	

}
