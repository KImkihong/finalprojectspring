package data.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.ChefDto;

@Repository
public class ChefDao extends SqlSessionDaoSupport implements ChefDaoInter {

	@Override
	public void insertChef(ChefDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfChef", dto);
	}
	
	@Override
	public void deleteChef(String email) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfChef", email);
	}

	@Override
	public int login(String email, String pass) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pass", pass);
		return getSqlSession().selectOne("login", map);
	}

	@Override
	public void updateInfo(ChefDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().update("updateNormalinfoOfChef", dto);
	}

	@Override
	public void updatePass(String pass, String newpass, String email) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pass", pass);
		map.put("newpass", newpass);
		getSqlSession().update("updatePassOfChef", map);
	}

	@Override
	public void receiveNews(String receiver, String provider) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("receiver", receiver);
		map.put("provider", provider);
		getSqlSession().insert("recieveNews", map);
	}

	@Override
	public void cutNews(String receiver, String provider) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("receiver", receiver);
		map.put("provider", provider);
		getSqlSession().delete("cutNews", map);
	}

	@Override
	public int checkEamil(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("checkEmailOfChef", email);
	}

	@Override
	public int checkNickname(String nickname) {
		// TODO uto-generated method stub
		return getSqlSession().selectOne("checkNickOfChef", nickname);
	}

	@Override
	public String findId(String name, String birthday) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("birthday", birthday);
		return getSqlSession().selectOne("findIdOfChef", map);
	}

}
