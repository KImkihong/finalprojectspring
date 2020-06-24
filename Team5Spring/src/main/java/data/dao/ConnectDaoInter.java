package data.dao;


public interface ConnectDaoInter {
	//��ũ��
	public int checkScrap(String email, int rec_num);
	public void insertScrap(String email, int rec_num);
	public void deleteScrap(String email, int rec_num);
	public int getCountScrap(int rec_num);
	public String getEmail(int rec_num);
	public void updateScrapCount(String email,int check);
	
	//�ҽĹޱ�
	public int checkNews(String receiver, String provider);
	public void receiveNews(String receiver, String provider);
	public void cutNews(String receiver, String provider);
	public String getNickname(String provider);
	public void upNewsCount(String email);
	public void downNewsCount(String email);
	//���ƿ�
	public int checkJoayo(String email, int rec_num);
	public void insertJoayo(String email, int rec_num);
	public void deleteJoayo(String email, int rec_num);
	public int getCountJoayo(int rec_num);
	public void updateJoayoCount(String email,int check);
}
