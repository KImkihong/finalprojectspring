package data.dao;

import java.util.List;

import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

public interface RecipeDaoInter {
	//selectList
	public List<RecipeDto> getList(int start,int end,String search,String food_cate,String sort);
	public List<Integer> getRec_nums(int start,int end,String search,String sort,String food_cate);
	public int getRecipeCount(String search,String food_cate);
	public int getRec_numCount(String search,String food_cate);
	public void updateReadcount(int rec_num);
	//insertData
	public int getMaxCount();
	public void insertRecipe(RecipeDto rdto);
	public void insertIngre(IngredientDto idto);
	public void insertOrder(RecipeOrderDto odto);
	//oneSelectData
	public RecipeDto getSelectedRecipe(int rec_num);
	public List<IngredientDto> getIngre(int rec_num);
	public List<RecipeOrderDto> getOrder(int rec_num);
	//deleteData
	public void deleteOrder(int rec_num);
	public void deleteIngre(int rec_num);
	public void deleteRecipe(int rec_num);
	//count
	public int getReadcount(int rec_num);
	//update
	public void updateRecipe(RecipeDto dto);
	public List<String> getImage(int rec_num);
	
}
