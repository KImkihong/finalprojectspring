package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.CommentDto;
import data.dto.RecipeDto;

@Repository
public class MypageDao extends SqlSessionDaoSupport implements MypageDaoInter {

	@Override
	public List<RecipeDto> getMyRecipe(String email,int start,int end) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("email",email);
		return getSqlSession().selectList("getMyRecipe", map);
	}

	@Override
	public List<Integer> getMyScrap(String email,int start,int end) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("email",email);
		return getSqlSession().selectList("getMyScrap", map);
	}

	@Override
	public RecipeDto getMyScrapRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMyScrapRecipe", rec_num);
	}

	@Override
	public List<CommentDto> getMyComment(String email,int start,int end) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("end",end);
		map.put("email",email);
		return getSqlSession().selectList("getMyComment", map);
	}

	@Override
	public RecipeDto getCommentRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCommentRecipe", rec_num);
	}

	@Override
	public int getMyRecipeCount(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMyRecipeCount", email);
	}

	@Override
	public int getMyScrapCount(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMyScrapCount", email);
	}

}
