package spring.form.acorn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.RefriDaoInter;

@RestController
@CrossOrigin
public class RefriController {
	
	@Autowired
	private RefriDaoInter dao;
	
	@PostMapping("/refri/put")
	public void putingre(@RequestParam String email, @RequestParam String refrig_name) {
		dao.insertRefri(email, refrig_name);
	}
	
	@PostMapping("/refri/delete")
	public void deleteingre(@RequestParam String email, @RequestParam String refrig_name) {
		dao.deleteRefri(email, refrig_name);
	}
	
	@GetMapping("/refri/delete")
	public List<String> getingre(@RequestParam String email){
		return dao.getRefri(email);
	}
}
