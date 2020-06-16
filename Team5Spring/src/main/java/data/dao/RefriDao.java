package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

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
	public void deleteRefri(String email, String refrig_name) {
		// TODO Auto-generated method stub
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("refrig_name", refrig_name);
		getSqlSession().delete("deleteRefrig", map);
	}

	@Override
	public List<String> getRefri(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getRefrig", email);
	}

}
