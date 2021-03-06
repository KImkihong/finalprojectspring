package data.dao;

import java.util.List;

import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

public interface RecipeDaoInter {
	//selectList
	public List<RecipeDto> getList(int start,int end);
	public int getTotalCount();
	//oneSelectData
	public RecipeDto getSelectedRecipe(int rec_num);
	public List<IngredientDto> getIngre(int rec_num);
	public List<RecipeOrderDto> getOrder(int rec_num);
	//deleteData
	public void deleteOrder(int rec_num);
	public void deleteIngre(int rec_num);
	public void deleteRecipe(int rec_num);
}
