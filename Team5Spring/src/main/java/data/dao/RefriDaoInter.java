package data.dao;

import java.util.List;

public interface RefriDaoInter {
	public void insertRefri(String email, String refrig_name);
	public void deleteRefri(String email, String refrig_name);
	public List<String> getRefri(String email);
}
