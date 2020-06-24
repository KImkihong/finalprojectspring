package data.dao;

import java.util.List;

import data.dto.CommentDto;
import data.dto.RecipeDto;

public interface MypageDaoInter {
	//���� �� ������
	public List<RecipeDto> getMyRecipe(String email,int start,int end);
	//���� ��ũ���� ������
	public List<Integer> getMyScrap(String email);
	public RecipeDto getMyScrapRecipe(int rec_num);
	//���� �� ���+�� ����� ������
	public List<CommentDto> getMyComment(String email);
	public RecipeDto getCommentRecipe(int rec_num);
}
