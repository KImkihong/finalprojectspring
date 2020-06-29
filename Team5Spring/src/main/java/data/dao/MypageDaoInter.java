package data.dao;

import java.util.List;

import data.dto.RecipeDto;

public interface MypageDaoInter {
	//���� �� ������
	public List<RecipeDto> getMyRecipe(String email,int start,int end);
	//���� ��ũ���� ������
	public List<Integer> getMyScrap(String email,int start,int end);
	public RecipeDto getMyScrapRecipe(int rec_num);
	//ī��Ʈ ����
	public int getMyRecipeCount(String email);
	public int getMyScrapCount(String email);
}
