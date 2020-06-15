package data.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
	public String findId(String name, String hp) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("hp", hp);
		return getSqlSession().selectOne("findIdOfChef", map);
	}

	@Override
	public String findPass(String name, String email) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		return getSqlSession().selectOne("findPassOfChef", map);
	}

	@Override
	public String RandomPass() {
		// TODO Auto-generated method stub
		Random rnd =new Random();
	    StringBuffer buf =new StringBuffer();

	    for(int i=0;i<10;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
	    }	      
	    return buf.toString();
	}

	@Override
	public ChefDto getChef(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getDataOfChef", email);
	}

	@Override
	public String getprofile(String email) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getprofile", email);
	}

}
