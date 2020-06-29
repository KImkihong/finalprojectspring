package data.dao;

import java.util.HashMap;
import java.util.List;

import data.dto.IngredientDto;
import data.dto.RecipeDto;

public interface RefriDaoInter {
	public void insertRefri(String email, String refrig_name);
	public void deleteRefri(int refrig_num);
	public List<HashMap<String, Object>> getRefri(String email);
	public String getRefriIngre(int refrig_num);
	public List<Integer> getRec_nums(String[] ingrelist);
	public RecipeDto getIngreRecipe(int rec_num);
	public List<IngredientDto> getIngreOfRefri(int rec_num);
}
