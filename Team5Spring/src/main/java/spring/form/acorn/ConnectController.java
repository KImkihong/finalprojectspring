package spring.form.acorn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.ConnectDaoInter;

@RestController
@CrossOrigin
public class ConnectController {
	
	@Autowired
	private ConnectDaoInter dao;
	
	@GetMapping("/main.do")
	public String start() {
		return "hello";
	}
	
	@GetMapping("/connect/scrapcheck")
	public int checkscrap(@RequestParam String email, @RequestParam int rec_num) {
		int check =dao.checkScrap(email, rec_num);
		return check;
	}
	
	@GetMapping("/connect/scrap")
	public void onscrap(@RequestParam String email, @RequestParam int rec_num) {
		String chefemail=dao.getEmail(rec_num);
		int check =dao.checkScrap(email, rec_num);
		System.out.println(check);
		if(check==0) {
			dao.insertScrap(email, rec_num);
		}
		else {
			dao.deleteScrap(email, rec_num);
		}
		dao.updateScrapCount(chefemail, check);
	}
	
	@GetMapping("/connect/newscheck")
	public int checkjoayo(@RequestParam String provider, @RequestParam String receiver) {
		int check =dao.checkNews(receiver, provider);
		return check;
	}
	
	@GetMapping("/connect/onnews")
	public String onnews(@RequestParam String provider, @RequestParam String receiver) {
		dao.receiveNews(receiver, provider);
		dao.upNewsCount(provider);
		String msg = dao.getNickname(provider)+"님의 소식받기가 완료되었습니다";
		return msg;
	}
	
	@GetMapping("/connect/offnews")
	public String offnews(@RequestParam String provider, @RequestParam String receiver) {
		dao.cutNews(receiver, provider);
		dao.downNewsCount(provider);
		String msg = dao.getNickname(provider)+"님의 소식받기를 취소했습니다";
		return msg;
	}
	
	@GetMapping("/connect/joayocheck")
	public int checkjoayo(@RequestParam String email, @RequestParam int rec_num) {
		int check =dao.checkJoayo(email, rec_num);
		return check;
	}
	
	@GetMapping("/connect/joayo")
	public int onjoa(@RequestParam String email, @RequestParam int rec_num) {
		String chefemail=dao.getEmail(rec_num);
		int check =dao.checkJoayo(email, rec_num);
		if(check==0) {
			dao.insertJoayo(email, rec_num);
		}
		else {
			dao.deleteJoayo(email, rec_num);
		}
		dao.updateJoayoCount(chefemail, check);
		return dao.getCountJoayo(rec_num);
	}
}
