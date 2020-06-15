package spring.form.acorn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.MypageDaoInter;
import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

@RestController
@CrossOrigin
public class MypageController {
	
	@Autowired
	private MypageDaoInter dao;
	
	@GetMapping("/mypage/recipe")
	public List<RecipeDto> getMyrecipe(@RequestParam String email){
		List<RecipeDto> list = dao.getMyRecipe(email);
		return list;
	}
	
	@GetMapping("/mypage/scrap")
	public List<RecipeDto> getMyscrap(@RequestParam String email){
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		List<Integer> rec_nums = dao.getMyScrap(email);
		for(int rec_num : rec_nums) {
			RecipeDto dto = dao.getMyScrapRecipe(rec_num);
			list.add(dto);
		}
		return list;
	}
	
	@GetMapping("/mypage/comment")
	public 
	
	
}
