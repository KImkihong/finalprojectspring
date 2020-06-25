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
import data.dto.CommentDto;
import data.dto.RecipeDto;

@RestController
@CrossOrigin
public class MypageController {
	
	@Autowired
	private MypageDaoInter dao;
	
	@GetMapping("/mypage/recipe")
	public List<RecipeDto> getMyrecipe(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		final int end=3;
		List<RecipeDto> list = dao.getMyRecipe(email,scroll*3,end);
		return list;
	}
	
	@GetMapping("/mypage/scrap")
	public List<RecipeDto> getMyscrap(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		int start = scroll*3;
		int end = start+3;
		List<RecipeDto> list = new ArrayList<RecipeDto>();
		List<Integer> rec_nums = dao.getMyScrap(email,start,end);
		for(int rec_num:rec_nums) {
			RecipeDto dto = dao.getMyScrapRecipe(rec_num);
			list.add(dto);
		}
		return list;
	}
	
	@GetMapping("/mypage/comment")
	public List<HashMap<String,Object>> getMyComment(@RequestParam String email,
			@RequestParam(required=false, defaultValue="0") int scroll){
		int start = scroll*5;
		int end = start+5;
		List<HashMap<String,Object>> list= new ArrayList<HashMap<String,Object>>();
		List<CommentDto> clist = dao.getMyComment(email,start,end);
		for(CommentDto dto:clist) {
			HashMap<String,Object> map = new HashMap<String, Object>();
			String comment = dto.getContent();
			map.put("comment",comment);
			RecipeDto recipe = dao.getCommentRecipe(dto.getRec_num());
			map.put("RecipeDto",recipe);
			list.add(map);
		}
		return list;
	}
}
