package spring.form.acorn;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import data.dao.ChefDaoInter;
import data.dto.ChefDto;


@RestController
@CrossOrigin
public class ChefController {
	
	@Autowired
	private ChefDaoInter dao;

	@PostMapping("/chef/regist")
	public Object register(@RequestBody ChefDto dto) {
		ArrayList<String> list=new ArrayList<String>();
		
		
		return list;
	}
}
