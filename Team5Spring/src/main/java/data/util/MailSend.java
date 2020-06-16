package data.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSend {
	private JavaMailSender mailSender;	
	
	public MailSend(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}


	public int MailGo(String mailaddr,String subject,String content) {
		MimeMessage message = mailSender.createMimeMessage();
	      int success=0;
	      try {
	    	  MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	    	  

            messageHelper.setFrom("acorn555@naver.com"); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(mailaddr); // 받는사람 이메일
            messageHelper.setSubject(subject); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용
            mailSender.send(message);
           
            success=1;
	          
	         /* 여러명에게 보내기
	          * String []addr = mailaddr.split(",");
	          * for(String a:addr) {
	          * message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(
	          * mailaddr));//보낼 메일주소
	          * mailSender.send(message);
	          * }
	          * model.addAttribute("msg",
	          * mailaddr+"에 메일을 보냈습니다");
	          */
	      } catch (MessagingException e) {
	         // TODO Auto-generated catch block
	         System.out.println(e.getMessage());
	      }
	      
	      return success;
	}
}
