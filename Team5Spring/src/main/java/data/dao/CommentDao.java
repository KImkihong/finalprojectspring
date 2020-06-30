package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.CommentDto;

@Repository
public class CommentDao extends SqlSessionDaoSupport implements CommentDaoInter {

	@Override
	public int getMaxNum() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("maxNumOfComment");
	}

	@Override
	public void updateRestep(int regroup, int restep) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("regroup", regroup);
		map.put("restep", restep);
		getSqlSession().update("updateRestepOfCommenet", map);
	}

	@Override
	public void insertComment(CommentDto dto) {
		// TODO Auto-generated method stub
		int regroup =dto.getRegroup();
		int restep = dto.getRestep();
		int relevel = dto.getRelevel();
		//System.out.println(dto.getNum());
		
		//새글인지 답글인지 판단
		if(dto.getCom_num()==0) {	//새글
			//새글인 경우 그룹번호는 num의 max값 +1
			regroup = this.getMaxNum()+1;
			relevel = 0;
			restep = 0;
		}else {
			//같은 그룹 중 restep 보다 큰 데이터는 1증가
			this.updateRestep(regroup, restep);
			//db에는 +해서 저장
			if(relevel<2)
				relevel++;
			restep++;
		}
		
		//dto에 새로 구한 3가지 넣어주기
		dto.setRegroup(regroup);
		dto.setRelevel(relevel);
		dto.setRestep(restep);
		
		getSqlSession().insert("insertOfComment", dto);
	}

	@Override
	public List<CommentDto> getCommentlist(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getListOfComment", rec_num);
	}

	@Override
	public void deleteCommenet(int com_num) {
		// TODO Auto-generated method stub
		getSqlSession().update("deleteComment", com_num);
	}

	@Override
	public CommentDto getComment(int com_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getComment", com_num) ;
	}

	@Override
	public int getCount(int rec_num) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getCount", rec_num);
	}
	
}
