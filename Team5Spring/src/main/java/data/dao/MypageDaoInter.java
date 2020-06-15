package data.dao;

import java.util.List;

import data.dto.RecipeDto;

public interface MypageDaoInter {
	public List<RecipeDto> getMyRecipe(String email);
	public List<Integer> getMyScrap(String email);
	public RecipeDto getMyScrapRecipe(int rec_num);
}
