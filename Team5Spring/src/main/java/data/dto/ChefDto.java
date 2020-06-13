package data.dto;


import org.springframework.web.multipart.MultipartFile;


public class ChefDto {
	private String name;
	private String email;
	private String pass;
	private String nickname;
	private String birth;
	private MultipartFile profileimage;
	private String profile;
	public MultipartFile getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(MultipartFile profileimage) {
		this.profileimage = profileimage;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
