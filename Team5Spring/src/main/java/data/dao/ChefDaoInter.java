package data.dao;

import data.dto.ChefDto;

public interface ChefDaoInter {
	public void insertChef(ChefDto dto);
	public void deleteChef(String email);
	public int login(String email,String pass);
	public void updateInfo(ChefDto dto);
	public void updatePass(String pass, String newpass, String email);
	public void receiveNews(String receiver, String provider);
	public void cutNews(String receiver, String provider);
	public int checkEamil(String email);
	public int checkNickname(String nickname);
	public String findId(String name, String birthday);
}
