package spring.form.acorn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.RankingDaoInter;
import data.dto.ChefDto;

@RestController
@CrossOrigin
public class RankingController {
	
	@Autowired
	private RankingDaoInter dao;
	
	@GetMapping("/ranking/sorting")
	public List<ChefDto> chefSorting(@RequestParam String standard){
		return dao.chefSorting(standard);
	}
}
