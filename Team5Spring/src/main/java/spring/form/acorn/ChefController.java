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
	
	//아이디 유효성 검사
	@PostMapping("/chef/checkid")
	public int checkid(@RequestParam String email) {
		int possible = dao.checkEamil(email);
		if(possible==0)
			possible=1;
		else if(possible==1)
			possible=0;		
		
		return possible;
	}
	//닉네임 유효성 검사
	@PostMapping("/chef/checknick")
	public int checknick(@RequestParam String nickname) {
		int possible = dao.checkNickname(nickname);
		if(possible==0)
			possible=1;
		else if(possible==1)
			possible=0;		
		
		return possible;
	}
	
	//가입
	@RequestMapping(value="/chef/regist", consumes = {"multipart/form-data"} ,method = RequestMethod.POST)
	public int register(MultipartHttpServletRequest request, @ModelAttribute("ChefDto") ChefDto dto, BindingResult result) {
	
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
	//탈퇴
	@PostMapping("/chef/withdraw")
	public void withdraw(MultipartHttpServletRequest request, @RequestParam String email, @RequestParam String reason) {
	
			String preprofile=dao.getprofile(email);
			String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
			File file = new File(path+"\\"+preprofile);
			if(file.exists())
				file.delete();
			dao.insertWithdraw(email);
			dao.updateWithdraq(email, reason);
			dao.deleteChef(email);
	}
	//로그인
	@PostMapping("/chef/login")
	public int login(@RequestParam String email, @RequestParam String pass) {
		int success=dao.login(email, pass);
		return success;
	}
	//아이디 찾기
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
		String pass = dao.findPass(name, email);
		if(pass!=null) {
			String subject = "슬기로운 냉장고 비밀번호 변경안내";
			String newpass = dao.RandomPass();
			String content = "비밀번호가 변경되었습니다.\r새로운 비밀번호: "+newpass;
			MailSend mail = new MailSend(mailSender);
			int checkemail= mail.MailGo(email, subject, content);
			dao.updatePass(pass, newpass, email);
			if(checkemail==1)
				success=1;			
		}
		return success;
	}
	
	//일반정보 수정폼
	@GetMapping("/chef/modform")
	public ChefDto modform(@RequestParam String email) {
		ChefDto dto = dao.getChef(email);
		return dto;
	}
	
	//일반정보수정
	@RequestMapping(value="/chef/mod", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public int mod(MultipartHttpServletRequest request, @RequestParam int change, @ModelAttribute("ChefDto") ChefDto dto, BindingResult result) {
		String preprofile=dao.getprofile(dto.getEmail());
		if(change==1) {
			if(dto.getProfileimage()==null) {
				String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
				File file = new File(path+"\\"+preprofile);
				if(file.exists())
					file.delete();
				dto.setProfile("basic_user.png");				
			}else {
				String path=request.getSession().getServletContext().getRealPath("/WEB-INF/image/profile");
				String fileName = new Date().getTime()+"_"+dto.getProfileimage().getOriginalFilename();
				dto.setProfile(fileName);
				SpringFileWrite sfw = new SpringFileWrite();
				sfw.writeFileRename(dto.getProfileimage(), path, fileName);
				File file = new File(path+"\\"+preprofile);
				if(file.exists())
					file.delete();
			}
		}else {
			//사진이 안바꼈을 경우 기존 프로필 사진 저장해두기
			dto.setProfile(preprofile);
		}
		
		dao.updateInfo(dto);
		return 1;
	}	
	
	//비밀번호 변경
	@PostMapping("/chef/modpass")
	public int modpass(@RequestParam String pass, @RequestParam String newpass, @RequestParam String email) {
		int success=0;
		dao.updatePass(pass, newpass, email);
		success=1;
		return success;
	}
	
	@GetMapping("/chef/mypage")
	public ChefDto mypageInfo(@RequestParam String nick) {
		ChefDto dto = dao.getDataOfChefNick(nick);
		return dto;
	}	
	
}
