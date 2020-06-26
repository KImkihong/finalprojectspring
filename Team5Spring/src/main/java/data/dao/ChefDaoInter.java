package data.dao;

import data.dto.ChefDto;

public interface ChefDaoInter {
	//ȸ������
	public void insertChef(ChefDto dto);
	//Ż��
	public void deleteChef(String email);
	public void insertWithdraw(String email);
	public void updateWithdraq(String email,String reason);
	//�α���
	public int login(String email,String pass);
	//ȸ������ ������ ���� ����
	public ChefDto getChef(String email);
	//���������ʻ��� 
	public String getprofile(String email);
	//�Ϲ����� ����
	public void updateInfo(ChefDto dto);
	//�������
	public void updatePass(String pass, String newpass, String email);
	//�̸���(���̵�)�ߺ�üũ
	public int checkEamil(String email);
	//�г��� �ߺ�üũ
	public int checkNickname(String nickname);
	//���̵� ã��
	public String findId(String name, String hp);
	//���ã�� �Ҷ� ���� ������
	public String findPass(String name, String email);
	//���ο� ��й�ȣ �������� ����
	public String RandomPass();
	
	public ChefDto getDataOfChefNick(String nickname);
}
