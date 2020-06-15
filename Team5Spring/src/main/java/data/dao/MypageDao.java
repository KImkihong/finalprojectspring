package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.IngredientDto;
import data.dto.RecipeDto;
import data.dto.RecipeOrderDto;

@Repository
public class MypageDao extends SqlSessionDaoSupport implements MypageDaoInter {

	@Override
	public List<RecipeDto> getMyRecipe(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientDto> getIngre(int rec_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeOrderDto> getOrder(int rec_num) {
		// TODO Auto-generated method stub
		return null;
	}

}
