package spring.form.acorn;


import java.io.File;
import java.util.Date;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(value="/chef/regist", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public int register(MultipartHttpServletRequest request, @ModelAttribute ChefDto dto) {
	
		if(dto.getProfileimage()!=null) {
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
	public int withdraw(@RequestParam String email, @RequestParam String pass) {
		int success=0;
		int check = dao.login(email, pass);
		if(check==1)
			dao.deleteChef(email);
		return success;
	}
	//�α���
	@PostMapping("/chef/login")
	public int login(@RequestParam String email, @RequestParam String pass) {
		int success=dao.login(email, pass);
		return success;
	}
	//���̵� ã��
	@PostMapping("/chef/findid")
	public String findid(@RequestParam String name, @RequestParam String birthday) {
		String id = dao.findId(name, birthday);
		System.out.println(id);
		return id;
	}
	//���ã��
	@PostMapping("/chef/findpass")
	public int findPass(@RequestParam String name, @RequestParam String email) {
		int success=0;
		String pass = dao.findPass(name, email);
		if(pass!=null) {
			String subject = "����ο� ����� ��й�ȣ ����ȳ�";
			String newpass = dao.RandomPass();
			String content = "��й�ȣ�� ����Ǿ����ϴ�. ���ο� ��й�ȣ: "+newpass;
			MailSend mail = new MailSend();
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
		ChefDto dto = dao.getChef(email);
		return dto;
	}
	
	//�Ϲ���������
	@RequestMapping(value="/chef/mod", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public int mod(MultipartHttpServletRequest request, @ModelAttribute ChefDto dto) {
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
