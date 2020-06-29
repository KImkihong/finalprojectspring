package data.dao;

import data.dto.ChefDto;

public interface ChefDaoInter {
	//회원가입
	public void insertChef(ChefDto dto);
	//탈퇴
	public void deleteChef(String email);
	public void insertWithdraw(String email);
	public void updateWithdraq(String email,String reason);
	//로그인
	public int login(String email,String pass);
	//회원정보 수정시 기존 정보
	public ChefDto getChef(String email);
	//기존프로필사진 
	public String getprofile(String email);
	//일반정보 수정
	public void updateInfo(ChefDto dto);
	//비번수정
	public void updatePass(String pass, String newpass, String email);
	//이메일(아이디)중복체크
	public int checkEamil(String email);
	//닉네임 중복체크
	public int checkNickname(String nickname);
	//아이디 찾기
	public String findId(String name, String hp);
	//비번찾기 할때 기존 비번얻기
	public String findPass(String name, String email);
	//새로운 비밀번호 랜덤문자 생성
	public String RandomPass();
	
	public ChefDto getDataOfChefNick(String nickname);
}
