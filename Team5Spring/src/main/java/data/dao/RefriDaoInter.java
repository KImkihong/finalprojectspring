package data.dao;

import java.util.HashMap;
import java.util.List;

import data.dto.RecipeDto;

public interface RefriDaoInter {
	public void insertRefri(String email, String refrig_name);
	public void deleteRefri(int refrig_num);
	public List<HashMap<String, String>> getRefri(String email);
	public String getRefriIngre(int refrig_num);
	public List<Integer> getRec_nums(String[] ingrelist);
	public RecipeDto getIngreRecipe(int rec_num);
}
