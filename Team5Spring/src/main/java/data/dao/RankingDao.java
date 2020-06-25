package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.ChefDto;
import data.dto.RecipeDto;

@Repository
public class RankingDao extends SqlSessionDaoSupport implements RankingDaoInter {

	@Override
	public List<ChefDto> chefSorting(String standard,int start,int end) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("standard",standard);
		return getSqlSession().selectList("chefSorting", map);
	}

	@Override
	public List<String> getProviders(String receiver,int start,int end) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("receiver",receiver);
		return getSqlSession().selectList("getProviders", map);
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
