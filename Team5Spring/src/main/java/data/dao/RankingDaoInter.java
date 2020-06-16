package data.dao;

import java.util.List;

import data.dto.ChefDto;

public interface RankingDaoInter {
	public List<ChefDto> chefSorting(String standard);
}
