package spring.form.acorn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dao.RefriDaoInter;
import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.util.TimeDiffrence;

@RestController
@CrossOrigin
public class RefriController {
	
	@Autowired
	private RefriDaoInter dao;
	
	@PostMapping("/refri/put")
	public void putingre(@RequestParam String email, @RequestParam String refrig_name) {
		dao.insertRefri(email, refrig_name);
	}
	
	@GetMapping("/refri/delete")
	public void deleteingre(@RequestParam int refrig_num) {
		dao.deleteRefri(refrig_num);
	}
	
	@GetMapping("/refri/list")
	public List<HashMap<String, Object>> getingre(@RequestParam String email){
		return dao.getRefri(email);
	}
	
	@PostMapping("/refri/search")
	public List<RecipeDto> search(@RequestParam List<Integer> refrig_num) {
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		
		String [] ingrelist = new String[refrig_num.size()];
		
		for(int i=0;i<ingrelist.length;i++) {
			ingrelist[i] = dao.getRefriIngre(refrig_num.get(i));
		}
		
		List<Integer> numList = dao.getRec_nums(ingrelist);
		TimeDiffrence td = new TimeDiffrence();
		for(int num:numList) {
			RecipeDto dto = dao.getIngreRecipe(num);
			String timeDiffer = td.formatTimeString(dto.getWriteday());
			dto.setTimeDiffer(timeDiffer);
			List<IngredientDto> ilist = dao.getIngreOfRefri(num);
			dto.setIngreList(ilist);
			list.add(dto);
			System.out.println(dto.getIngreList().get(0).getIngre_name());
			System.out.println(dto.getIngreList().get(0).getSort());
		}
		return list;
	}
}
