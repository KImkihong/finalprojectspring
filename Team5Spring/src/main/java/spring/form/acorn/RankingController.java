package spring.form.acorn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.RankingDaoInter;
import data.dto.ChefDto;
import data.dto.RecipeDto;
import data.util.TimeDiffrence;

@RestController
@CrossOrigin
public class RankingController {
	
	@Autowired
	private RankingDaoInter dao;	
	
	
	@GetMapping("/ranking/sorting")
	public List<ChefDto> chefSorting(){
		return dao.chefSorting();
	}
	
	@GetMapping("/ranking/news")
	public List<HashMap<String,Object>> mynews(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		int start = scroll*4;
		int end = start+4;
		List<HashMap<String,Object>> list= new ArrayList<HashMap<String,Object>>();
		List<String> providerList= dao.getProviders(email,start,end);
		TimeDiffrence td = new TimeDiffrence();
		for(String provider:providerList) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			ChefDto chef = dao.getProviderInfo(provider);
			map.put("chef",chef);
			List<RecipeDto> recipes = dao.getProvderRecipe(provider);			
			for(RecipeDto dto:recipes) {
				String timeDiffer = td.formatTimeString(dto.getWriteday());
				dto.setTimeDiffer(timeDiffer);
			}
			map.put("recipes",recipes);
			list.add(map);
		}
		return list;
	}
}
