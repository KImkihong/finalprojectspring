package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.CommentDto;
import data.dto.RecipeDto;

@Repository
public class MypageDao extends SqlSessionDaoSupport implements MypageDaoInter {

	@Override
	public List<RecipeDto> getMyRecipe(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getMyRecipe", email);
	}

	@Override
	public List<Integer> getMyScrap(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getMyScrap", email);
	}

	@Override
	public RecipeDto getMyScrapRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getMyScrapRecipe", rec_num);
	}

	@Override
	public List<CommentDto> getMyComment(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getMyComment", email);
	}

	@Override
	public RecipeDto getCommentRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCommentRecipe", rec_num);
	}

}
