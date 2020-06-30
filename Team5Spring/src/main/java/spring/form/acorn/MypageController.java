package spring.form.acorn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.MypageDaoInter;
import data.dto.RecipeDto;
import data.util.TimeDiffrence;

@RestController
@CrossOrigin
public class MypageController {
	
	@Autowired
	private MypageDaoInter dao;
	
	@GetMapping("/mypage/recipe")
	public HashMap<String,Object> getMyrecipe(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		final int end=5;
		int count=0;
		List<RecipeDto> list = dao.getMyRecipe(email,scroll*5,end);
		count=dao.getMyRecipeCount(email);
		TimeDiffrence td = new TimeDiffrence();
		for(RecipeDto dto:list) {
			String timeDiffer = td.formatTimeString(dto.getWriteday());
			dto.setTimeDiffer(timeDiffer);
		}		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);		
		return map;
	}
	
	@GetMapping("/mypage/scrap")
	public HashMap<String,Object> getMyscrap(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		int start = scroll*5;
		int end = start+5;
		int count=0;
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		List<Integer> rec_nums = dao.getMyScrap(email,start,end);
		count=dao.getMyScrapCount(email);
		for(int rec_num:rec_nums) {
			RecipeDto dto = dao.getMyScrapRecipe(rec_num);
			list.add(dto);
		}
		TimeDiffrence td = new TimeDiffrence();
		for(RecipeDto dto:list) {
			String timeDiffer = td.formatTimeString(dto.getWriteday());
			dto.setTimeDiffer(timeDiffer);
		}		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);		
		return map;
	}
	
}
