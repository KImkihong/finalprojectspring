package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.ChefDto;
import data.dto.RecipeDto;

@Repository
public class RankingDao extends SqlSessionDaoSupport implements RankingDaoInter {

	@Override
	public List<ChefDto> chefSorting(String standard) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("chefSorting", standard);
	}

	@Override
	public List<String> getProviders(String receiver) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getProviders", receiver);
	}

	@Override
	public ChefDto getProviderInfo(String provider) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getProviderInfo", provider);
	}

	@Override
	public List<RecipeDto> getProvderRecipe(String provider) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getProvderRecipe", provider);
	}

}
