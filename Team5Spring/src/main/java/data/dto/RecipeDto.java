package data.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class RecipeDto {
	private int rec_num;
	private String portion;
	private String time;
	private String difficult;
	private String repre_photo;
	private MultipartFile repre_photofile;
	private String comp_photo;
	private List<MultipartFile> comp_photoList;	
	private String food_cate;
	private String subject;
	private String summary;
	private int readcount;
	private String writeday;
	private String email;
	private List<RecipeOrderDto> orderList;
	private List<IngredientDto> ingreList;
	public MultipartFile getRepre_photofile() {
		return repre_photofile;
	}
	public void setRepre_photofile(MultipartFile repre_photofile) {
		this.repre_photofile = repre_photofile;
	}
	
	public List<MultipartFile> getComp_photoList() {
		return comp_photoList;
	}
	public void setComp_photoList(List<MultipartFile> comp_photoList) {
		this.comp_photoList = comp_photoList;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}	
	public List<IngredientDto> getIngreList() {
		return ingreList;
	}
	public void setIngreList(List<IngredientDto> ingreList) {
		this.ingreList = ingreList;
	}
	public List<RecipeOrderDto> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<RecipeOrderDto> orderList) {
		this.orderList = orderList;
	}
	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getPortion() {
		return portion;
	}
	public void setPortion(String portion) {
		this.portion = portion;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDifficult() {
		return difficult;
	}
	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}
	public String getRepre_photo() {
		return repre_photo;
	}
	public void setRepre_photo(String repre_photo) {
		this.repre_photo = repre_photo;
	}
	public String getComp_photo() {
		return comp_photo;
	}
	public void setComp_photo(String comp_photo) {
		this.comp_photo = comp_photo;
	}
	public String getFood_cate() {
		return food_cate;
	}
	public void setFood_cate(String food_cate) {
		this.food_cate = food_cate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
