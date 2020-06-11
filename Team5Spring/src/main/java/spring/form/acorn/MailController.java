package spring.form.acorn;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class MailController {
	@Autowired
	   private JavaMailSender mailSender;
	   
	   
	   @PostMapping("/mail/result.do")
	   public String result(
	         @RequestParam String mailaddr,
	         @RequestParam String subject,
	         @RequestParam String content
	         ){
	      MimeMessage message = mailSender.createMimeMessage();
	      String msg="";
	      try {
	    	  MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	    	  

              messageHelper.setFrom("������ ��� ���̹� ���� �ּ�"); // �����»�� �����ϸ� �����۵��� ����
              messageHelper.setTo(mailaddr); // �޴»�� �̸���
              messageHelper.setSubject(subject); // ���������� ������ �����ϴ�
              messageHelper.setText(content); // ���� ����
              mailSender.send(message);
             
              msg = "����";
	          
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
	         msg = "���� �����⿡ �����Ͽ����ϴ�"+e.getMessage();
	      }
	      
	      return msg;
	   }
}
