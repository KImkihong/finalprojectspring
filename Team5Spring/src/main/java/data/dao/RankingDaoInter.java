package data.dao;

import java.util.List;

import data.dto.ChefDto;
import data.dto.RecipeDto;

public interface RankingDaoInter {
	public List<ChefDto> chefSorting(String standard,int start, int end);
	
	public List<String> getProviders(String receiver);
	public ChefDto getProviderInfo(String provider);
	public List<RecipeDto> getProvderRecipe(String provider);
}
