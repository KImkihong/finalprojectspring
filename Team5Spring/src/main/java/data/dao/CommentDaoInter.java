package data.dao;

import java.util.List;

import data.dto.CommentDto;

public interface CommentDaoInter {
	public int getMaxNum();
	public void updateRestep(int regroup, int restep);
	public void insertComment(CommentDto dto);
	public List<CommentDto> getCommentlist(int rec_num, int start, int end);
	public void deleteCommenet(int com_num);
	public CommentDto getComment(int com_num);
	public int getCount(int rec_num);
}
