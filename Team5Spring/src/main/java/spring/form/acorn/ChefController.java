package spring.form.acorn;


import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import data.dao.ChefDaoInter;
import data.dto.ChefDto;
import data.util.MailSend;
import data.util.SpringFileWrite;


@RestController
@CrossOrigin
public class ChefController {
	
	@Autowired
	private ChefDaoInter dao;
	@Autowired
	JavaMailSender mailSender;
	
	//���̵� ��ȿ�� �˻�
	@PostMapping("/chef/checkid")
	public int checkid(@RequestParam String email) {
		int possible = dao.checkEamil(email);
		if(possible==0)
			possible=1;
		else if(possible==1)
			possible=0;		
		
		return possible;
	}
	//�г��� ��ȿ�� �˻�
	@PostMapping("/chef/checknick")
	public int checknick(@RequestParam String nickname) {
		int possible = dao.checkNickname(nickname);
		if(possible==0)
			possible=1;
		else if(possible==1)
			possible=0;		
		
		return possible;
	}
	
	//����
	@RequestMapping(value="/chef/regist", consumes = {"multipart/form-data"} ,method = RequestMethod.POST)
	public int register(MultipartHttpServletRequest request, @ModelAttribute("ChefDto") ChefDto dto, BindingResult result) {
	
		if(dto.getProfileimage()!=null) {
			System.out.println("path");
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
			String fileName = new Date().getTime()+"_"+dto.getProfileimage().getOriginalFilename();
			dto.setProfile(fileName);
			SpringFileWrite sfw = new SpringFileWrite();
			sfw.writeFileRename(dto.getProfileimage(), path, fileName);
		}else
			dto.setProfile("basic_user.png");	
		dao.insertChef(dto);	
		
		return 1;
	}
	//Ż��
	@PostMapping("/chef/withdraw")
	public void withdraw(MultipartHttpServletRequest request, @RequestParam String email, @RequestParam String reason) {
	
		System.out.println(email);
		System.out.println(reason);
		
			String preprofile=dao.getprofile(email);
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
			System.out.println(path);
			File file = new File(path+"\\"+preprofile);
			if(file.exists())
				file.delete();
			dao.insertWithdraw(email);
			dao.updateWithdraq(email, reason);
			dao.deleteChef(email);
	}
	//�α���
	@PostMapping("/chef/login")
	public int login(@RequestParam String email, @RequestParam String pass) {
		int success=dao.login(email, pass);
		return success;
	}
	//���̵� ã��
	@PostMapping("/chef/findid")
	public String findid(@RequestParam String name, @RequestParam String hp) {
		String id = dao.findId(name, hp);
		System.out.println(id);
		return id;
	}
	//비번찾기
	@PostMapping("/chef/findpass")
	public int findPass(@RequestParam String name, @RequestParam String email) {		
		int success=0;
		System.out.println(name);
		System.out.println(email);
		String pass = dao.findPass(name, email);
		if(pass!=null) {
			String subject = "슬기로운 냉장고 비밀번호 변경안내";
			String newpass = dao.RandomPass();
			String content = "비밀번호가 변경되었습니다. 새로운 비밀번호: "+newpass;
			MailSend mail = new MailSend(mailSender);
			int checkemail= mail.MailGo(email, subject, content);
			dao.updatePass(pass, newpass, email);
			if(checkemail==1)
				success=1;			
		}
		return success;
	}
	
	//�Ϲ����� ������
	@GetMapping("/chef/modform")
	public ChefDto modform(@RequestParam String email) {
		System.out.println(email);
		ChefDto dto = dao.getChef(email);
		return dto;
	}
	
	//�Ϲ���������
	@RequestMapping(value="/chef/mod", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public int mod(MultipartHttpServletRequest request, @ModelAttribute("ChefDto") ChefDto dto, BindingResult result) {
		
		
		String preprofile=dao.getprofile(dto.getEmail());
		if(dto.getProfileimage()==null)
			//������ �ȹٲ��� ��� ���� ������ ���� �����صα�
			dto.setProfile(preprofile);
		else {
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
			String fileName = new Date().getTime()+"_"+dto.getProfileimage().getOriginalFilename();
			dto.setProfile(fileName);
			SpringFileWrite sfw = new SpringFileWrite();
			sfw.writeFileRename(dto.getProfileimage(), path, fileName);
			File file = new File(path+"\\"+preprofile);
			if(file.exists())
				file.delete();
		}
		dao.updateInfo(dto);
		return 1;
	}	
	
	//��й�ȣ ����
	@PostMapping("/chef/modpass")
	public int modpass(@RequestParam String pass, @RequestParam String newpass, @RequestParam String email) {
		int success=0;
		dao.updatePass(pass, newpass, email);
		success=1;
		return success;
	}
}
