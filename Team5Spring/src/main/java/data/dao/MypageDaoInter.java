package data.dao;

import java.util.List;

import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

public interface MypageDaoInter {
	public List<RecipeDto> getMyRecipe(String email);
	public List<IngredientDto> getIngre(int rec_num);
	public List<RecipeOrderDto> getOrder(int rec_num);
}
