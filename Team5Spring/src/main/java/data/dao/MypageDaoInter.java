package data.dao;

import java.util.List;

import data.dto.RecipeDto;

public interface MypageDaoInter {
	//내가 쓴 레시피
	public List<RecipeDto> getMyRecipe(String email,int start,int end);
	//내가 스크랩한 레시피
	public List<Integer> getMyScrap(String email,int start,int end);
	public RecipeDto getMyScrapRecipe(int rec_num);
	//카운트 세기
	public int getMyRecipeCount(String email);
	public int getMyScrapCount(String email);
}
