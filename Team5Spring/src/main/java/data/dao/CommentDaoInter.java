package data.dao;

import data.dto.CommentDto;

public interface CommentDaoInter {
	public int getMaxNum();
	public void updateRestep(int regroup, int restep);
	public void insertComment(CommentDto dto);
}
