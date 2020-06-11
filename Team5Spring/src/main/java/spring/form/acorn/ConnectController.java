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
	
	@GetMapping("/connect/scrap")
	public String onscrap(@RequestParam String email, @RequestParam int rec_num) {
		String msg="";
		String chefemail=dao.getEmail(rec_num);
		int check =dao.checkScrap(email, rec_num);
		if(check==0) {
			dao.insertScrap(email, rec_num);
			msg = "��ũ���Ǿ����ϴ�. ������ ������� Ȯ�ΰ����մϴ�";
		}
		else {
			dao.deleteScrap(email, rec_num);
			msg = "��ũ���� ��� �Ǿ����ϴ�";
		}
		dao.updateScrapCount(chefemail, check);
		return msg;
	}
	
	@GetMapping("/connect/onnews")
	public String onnews(@RequestParam String provider, @RequestParam String receiver) {
		dao.receiveNews(receiver, provider);
		String msg = dao.getNickname(provider)+"���� �ҽĹޱⰡ �Ϸ�Ǿ����ϴ�";
		return msg;
	}
	
	@GetMapping("/connect/offnews")
	public String offnews(@RequestParam String provider, @RequestParam String receiver) {
		dao.cutNews(receiver, provider);
		String msg = dao.getNickname(provider)+"���� �ҽĹޱⰡ ��ҵǾ����ϴ�";
		return msg;
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
