package data.dao;

import java.util.List;

import data.dto.CommentDto;
import data.dto.RecipeDto;

public interface MypageDaoInter {
	//내가 쓴 레시피
	public List<RecipeDto> getMyRecipe(String email,int start,int end);
	//내가 스크랩한 레시피
	public List<Integer> getMyScrap(String email);
	public RecipeDto getMyScrapRecipe(int rec_num);
	//내가 쓴 댓글+그 댓글의 레시피
	public List<CommentDto> getMyComment(String email);
	public RecipeDto getCommentRecipe(int rec_num);
}
