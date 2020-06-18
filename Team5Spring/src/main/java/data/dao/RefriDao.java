package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.RecipeDto;

@Repository
public class RefriDao extends SqlSessionDaoSupport implements RefriDaoInter {

	@Override
	public void insertRefri(String email, String refrig_name) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("refrig_name", refrig_name);
		getSqlSession().insert("putRefrig", map);
	}

	@Override
	public void deleteRefri(int refrig_num) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteRefrig", refrig_num);
	}

	@Override
	public List<HashMap<String, String>> getRefri(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getRefrig", email);
	}

	@Override
	public String getRefriIngre(int refrig_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getRefrigIngre", refrig_num);
	}

	@Override
	public List<Integer> getRec_nums(String[] ingrelist) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getRefrigRec_nums", ingrelist);
	}

	@Override
	public RecipeDto getIngreRecipe(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getRefrigIngreRecipe", rec_num);
	}

}
