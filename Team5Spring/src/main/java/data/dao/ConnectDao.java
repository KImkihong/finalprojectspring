package data.dao;

import java.util.HashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectDao extends SqlSessionDaoSupport implements ConnectDaoInter {

	@Override
	public int checkScrap(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		return getSqlSession().selectOne("checkScrap", map);
	}

	@Override
	public String getEmail(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getEmail", rec_num);
	}

	@Override
	public void updateScrapCount(String email, int check) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("check",check);
		getSqlSession().update("updateScrapCount", map);
	}
	
	@Override
	public void insertScrap(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		getSqlSession().insert("insertScrap", map);
	}

	@Override
	public void deleteScrap(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		getSqlSession().delete("deleteScrap", map);
	}

	@Override
	public int getCountScrap(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCountOfScrap", rec_num);
	}

	@Override
	public void receiveNews(String receiver, String provider) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("receiver",receiver);
		map.put("provider",provider);
		getSqlSession().insert("receiveNews", map);
	}

	@Override
	public void cutNews(String receiver, String provider) {
		// TODO Auto-generated method stub
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("receiver",receiver);
		map.put("provider",provider);
		getSqlSession().delete("cutNews", map);
	}
	
	@Override
	public String getNickname(String provider) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getNickname", provider);
	}

	@Override
	public int checkJoayo(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		return getSqlSession().selectOne("checkJoayo", map);
	}	
	
	@Override
	public void insertJoayo(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		getSqlSession().insert("insertJoayo", map);
	}

	@Override
	public void deleteJoayo(String email, int rec_num) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("rec_num",rec_num);
		getSqlSession().delete("deleteJoayo", map);
	}

	@Override
	public int getCountJoayo(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCountOfJoayo", rec_num);
	}

	@Override
	public void updateJoayoCount(String email, int check) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("email",email);
		map.put("check",check);
		getSqlSession().update("updateJoayoCount", map);
	}

	@Override
	public void upNewsCount(String email) {
		// TODO Auto-generated method stub
		getSqlSession().update("upNewsCount", email);
	}

	@Override
	public void downNewsCount(String email) {
		// TODO Auto-generated method stub
		getSqlSession().update("downNewsCount", email);
	}		

}
