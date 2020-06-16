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

	    	  

            messageHelper.setFrom("acorn555@naver.com"); // �����»�� �����ϸ� �����۵��� ����
            messageHelper.setTo(mailaddr); // �޴»�� �̸���
            messageHelper.setSubject(subject); // ���������� ������ �����ϴ�
            messageHelper.setText(content); // ���� ����
            mailSender.send(message);
           
            success=1;
	          
	         /* �������� ������
	          * String []addr = mailaddr.split(",");
	          * for(String a:addr) {
	          * message.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(
	          * mailaddr));//���� �����ּ�
	          * mailSender.send(message);
	          * }
	          * model.addAttribute("msg",
	          * mailaddr+"�� ������ ���½��ϴ�");
	          */
	      } catch (MessagingException e) {
	         // TODO Auto-generated catch block
	         System.out.println(e.getMessage());
	      }
	      
	      return success;
	}
}
