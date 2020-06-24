package data.dto;

import org.springframework.web.multipart.MultipartFile;

public class CommentDto {
	private int com_num;
	private String content;
	private MultipartFile imagefile;
	private String image;
	private int regroup;
	private int restep;
	private int relevel;
	private String com_writeday;	
	private int rec_num;
	private String email;
	private String timeDiffer;
	public String getTimeDiffer() {
		return timeDiffer;
	}
	public void setTimeDiffer(String timeDiffer) {
		this.timeDiffer = timeDiffer;
	}
	public String getCom_writeday() {
		return com_writeday;
	}
	public void setCom_writeday(String com_writeday) {
		this.com_writeday = com_writeday;
	}
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getImagefile() {
		return imagefile;
	}
	public void setImagefile(MultipartFile imagefile) {
		this.imagefile = imagefile;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getRegroup() {
		return regroup;
	}
	public void setRegroup(int regroup) {
		this.regroup = regroup;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}

	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
