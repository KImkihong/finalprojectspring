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

	    	  

              messageHelper.setFrom("보내는 사람 네이버 메일 주소"); // 보내는사람 생략하면 정상작동을 안함
              messageHelper.setTo(mailaddr); // 받는사람 이메일
              messageHelper.setSubject(subject); // 메일제목은 생략이 가능하다
              messageHelper.setText(content); // 메일 내용
              mailSender.send(message);
             
              msg = "성공";
	          
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
	         msg = "메일 보내기에 실패하였습니다"+e.getMessage();
	      }
	      
	      return msg;
	   }
}
