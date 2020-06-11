package data.dto;


public class IngredientDto {
	private int rec_num;
	private String ingre_name;
	private String quantity;
	private String sort;
	private String ingre_cate;
	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getIngre_name() {
		return ingre_name;
	}
	public void setIngre_name(String ingre_name) {
		this.ingre_name = ingre_name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getIngre_cate() {
		return ingre_cate;
	}
	public void setIngre_cate(String ingre_cate) {
		this.ingre_cate = ingre_cate;
	}
}
