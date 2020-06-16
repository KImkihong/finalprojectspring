package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.ChefDto;

@Repository
public class RankingDao extends SqlSessionDaoSupport implements RankingDaoInter {

	@Override
	public List<ChefDto> chefSorting(String standard) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("chefSorting", standard);
	}

}
