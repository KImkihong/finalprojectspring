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

@RestController
@CrossOrigin
public class RankingController {
	
	@Autowired
	private RankingDaoInter dao;	
	
	
	@GetMapping("/ranking/sorting")
	public List<ChefDto> chefSorting(@RequestParam String standard,
			@RequestParam(required=false, defaultValue="0") int scroll){
		final int end =8;
		return dao.chefSorting(standard,scroll*8,end);
	}
	
	@GetMapping("/ranking/news")
	public List<HashMap<String,Object>> mynews(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		int start = scroll*4;
		int end = start+4;
		List<HashMap<String,Object>> list= new ArrayList<HashMap<String,Object>>();
		List<String> providerList= dao.getProviders(email);
		if(start>=providerList.size())
			return list;
		if(end>providerList.size())
			end = providerList.size();
		for(int i=start;i<end;i++) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			ChefDto chef = dao.getProviderInfo(providerList.get(i));
			map.put("chef",chef);
			List<RecipeDto> recipes = dao.getProvderRecipe(providerList.get(i));
			map.put("recipes",recipes);
			list.add(map);
		}
		
		return list;
	}
}
