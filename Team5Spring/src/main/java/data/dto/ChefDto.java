package data.dto;


import org.springframework.web.multipart.MultipartFile;


public class ChefDto {
	private String name;
	private String email;
	private String pass;
	private String nickname;
	private String hp;
	private String joindate;
	private MultipartFile profileimage;
	private String profile;
	private int scrapcount;
	private int joayocount;
	private int recipecount;
	private int score;
	public int getRecipecount() {
		return recipecount;
	}
	public void setRecipecount(int recipecount) {
		this.recipecount = recipecount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
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
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public int getScrapcount() {
		return scrapcount;
	}
	public void setScrapcount(int scrapcount) {
		this.scrapcount = scrapcount;
	}
	public int getJoayocount() {
		return joayocount;
	}
	public void setJoayocount(int joayocount) {
		this.joayocount = joayocount;
	}
	
}
