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
	public List<RecipeDto> getList(int start, int end,String search,String food_cate,String sort) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("search",search);
		map.put("food_cate",food_cate);
		map.put("sort",sort);
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

	@Override
	public int getMaxCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMaxCount");
	}

	@Override
	public void insertRecipe(RecipeDto rdto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfRecipe", rdto);
	}

	@Override
	public void insertIngre(IngredientDto idto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfingre", idto);
	}

	@Override
	public void insertOrder(RecipeOrderDto odto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfOrder", odto);
	}

	@Override
	public List<Integer> getRec_nums(int start, int end, String search,String sort,String food_cate) {
		// TODO Auto-generated method stub
		String searchlist = search.substring(1, search.length());
		String [] ingreList = searchlist.split("#");
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("ingreList",ingreList);
		map.put("count",ingreList.length-1);
		map.put("sort",sort);
		map.put("food_cate",food_cate);
		return getSqlSession().selectList("getRec_nums", map);
	}

	@Override
	public void updateReadcount(int rec_num) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateReadcount", rec_num);
	}

	@Override
	public int getReadcount(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCountOfread", rec_num);
	}

	@Override
	public int getRecipeCount(String search, String food_cate) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("search",search);
		map.put("food_cate",food_cate);
		return getSqlSession().selectOne("getRecipeCount", map);
	}

	@Override
	public int getRec_numCount(String search,String food_cate) {
		// TODO Auto-generated method stub
		String searchlist = search.substring(1, search.length());
		String [] ingreList = searchlist.split("#");
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("ingreList",ingreList);
		map.put("food_cate",food_cate);
		map.put("count",ingreList.length-1);
		return getSqlSession().selectOne("getRec_numsCount", map);
	}

	@Override
	public void updateRecipe(RecipeDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updaterecipe", dto);
	}

	@Override
	public List<String> getImage(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getImage", rec_num);
	}	

}
