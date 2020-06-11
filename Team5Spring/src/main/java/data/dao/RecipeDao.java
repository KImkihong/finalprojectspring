package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

@Repository
public class RecipeDao extends SqlSessionDaoSupport implements RecipeDaoInter {

	@Override
	public List<RecipeDto> getList(int start, int end) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("start",start);
		map.put("end",end);
		return getSqlSession().selectList("getRecipe",map);
	}
	
	@Override
	public RecipeDto getSelectedRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getSelectOfRecipe", rec_num);
	}

	@Override
	public List<IngredientDto> getIngre(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getIngre", rec_num);
	}

	@Override
	public List<RecipeOrderDto> getOrder(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getOrder", rec_num);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getTotalCount");
	}

	@Override
	public void deleteOrder(int rec_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOrder", rec_num);
	}

	@Override
	public void deleteIngre(int rec_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteIngredient", rec_num);
	}

	@Override
	public void deleteRecipe(int rec_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteRecipe", rec_num);
	}	

}
